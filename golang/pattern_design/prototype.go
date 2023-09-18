package pattern_design

type Cloneable interface {
	Clone() Cloneable
}

type Prototype struct {
	Data string
}

func (p *Prototype) Clone() Cloneable {
	return &Prototype{Data: p.Data}
}
