package es

import (
	"context"
	"encoding/json"
	"errors"
	"fmt"
	es "github.com/olivere/elastic/v7"
	"sync"
	"testing"
)

type Student struct {
	Name    string `json:"name"`
	Age     int    `json:"age"`
	Address string `json:"address"`
}

func TestA(t *testing.T) {
	client, _ := GetDefaultSearchClient()
	ctx := context.Background()
	//for i := 0; i < 10; i++ {
	//	_, err := client.IndexDoc(ctx, "can_test_001", &Student{
	//		Name:    "wangcan" + strconv.Itoa(i+1),
	//		Age:     i + 1,
	//		Address: "新湖路" + strconv.Itoa(i+10),
	//	})
	//	if err != nil {
	//		t.Fatal(err)
	//	}
	//}
	var students []*Student
	err := client.SearchDoc(
		ctx,
		"can_test_001",
		es.NewMatchQuery("name", "wangcan1"),
		func(data any) {
			var student *Student
			_ = json.Unmarshal(data.(json.RawMessage), &student)
			fmt.Println(student.Age)
			students = append(students, student)
		},
	)

	fmt.Println(students)
	t.Fatal(err)
}

type (
	SearchClient interface {
		CreateIndex(context.Context, string) error
		IndexDoc(context.Context, string, any) (bool, error)
		IndexDocByID(context.Context, string, string, any) (bool, error)
		GetDocByID(context.Context, string, string, any) error
		SearchDoc(context.Context, string, es.Query, func(any)) error
	}
)

type EsClient struct {
	*es.Client
}

var once sync.Once

var _ SearchClient = &EsClient{}

var defaultClient *EsClient

func GetDefaultSearchClient() (SearchClient, error) {
	var err error

	once.Do(func() {
		defaultClient = &EsClient{}
		defaultClient.Client, err = es.NewClient(
			es.SetURL("http://es.GCYyTFrL.ap-sg-1-general-x-General.test.dae.shopee.io:9205"),
			es.SetBasicAuth("infra_products_can", "gJIfOt!KsH"),
		)
	})

	return defaultClient, err
}

func (e *EsClient) CreateIndex(ctx context.Context, index string) error {
	_, err := e.Client.CreateIndex(index).Do(ctx)
	return err
}

func (e *EsClient) IndexDoc(ctx context.Context, index string, document any) (bool, error) {
	_, err := e.Client.Index().Index(index).BodyJson(document).Do(ctx)
	if err != nil {
		return false, err
	}

	return true, nil
}

func (e *EsClient) IndexDocByID(ctx context.Context, index string, id string, document any) (bool, error) {
	_, err := e.Client.Index().Index(index).Id(id).BodyJson(document).Do(ctx)
	if err != nil {
		return false, err
	}

	return true, nil
}

var EsNotFoundError error = errors.New("condition Not Found")

func (e *EsClient) GetDocByID(ctx context.Context, index string, id string, data any) error {
	do, err := e.Client.Get().Index(index).Id(id).Do(ctx)
	if err != nil {
		return err
	}

	if !do.Found {
		return EsNotFoundError
	}

	return json.Unmarshal(do.Source, &data)
}

func (e *EsClient) SearchDoc(ctx context.Context, index string, query es.Query, fn func(any)) error {
	do, err := e.Search(index).Query(query).Do(ctx)
	if err != nil {
		return err
	}

	if do.Hits.TotalHits.Value > 0 {
		for _, hit := range do.Hits.Hits {
			fn(hit.Source)
		}
	}

	return EsNotFoundError
}
