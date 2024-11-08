package main

import (
	"github.com/andlabs/ui"
)

func main() {
	err := ui.Main(func() {
		input := ui.NewConfirmValue("Are you sure?")
		if input.Value() {
			println("Confirmed")
		} else {
			println("Canceled")
		}
		ui.Quit()
	})
	if err != nil {
		panic(err)
	}
}
