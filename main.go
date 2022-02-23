package main

import (
	"fmt"
	"math/rand"
)

const ROWS, COLUMNS = 10, 10

func createBoard() [][]int {
	var b = make([][]int, ROWS)
	for i := 0; i < ROWS; i++ {
		b[i] = make([]int, COLUMNS)
	}
	return b
}
func generate(b [][]int) {
	for row := 0; row < ROWS; row++ {
		for column := 0; column < COLUMNS; column++ {
			b[row][column] = rand.Intn(2)
		}
	}
}

func nextGeneration(b [][]int) [][]int {
	nextGen := createBoard()
	for row := 0; row < ROWS; row++ {
		for column := 0; column < COLUMNS; column++ {
			alnCnt := countLiveNeighbours(b, row, column)
			step(b, nextGen, row, column, alnCnt)
		}
	}
	b = nextGen
	return b
}

func step(b, nextGen [][]int, row, column, alnCnt int) {
	val := b[row][column]

	if val == 1 {
		if alnCnt < 2 {
			nextGen[row][column] = 0
		} else if alnCnt == 2 || alnCnt == 3 {
			nextGen[row][column] = 1
		} else if alnCnt > 3 {
			nextGen[row][column] = 0
		}
	} else {
		if alnCnt == 3 {
			nextGen[row][column] = 1
		}
	}

}

func countLiveNeighbours(b [][]int, row, column int) int {
	sum := 0
	sum += getVal(b, row-1, column-1)
	sum += getVal(b, row-1, column)
	sum += getVal(b, row-1, column+1)

	sum += getVal(b, row, column-1)
	sum += getVal(b, row+1, column+1)

	sum += getVal(b, row+1, column-1)
	sum += getVal(b, row+1, column)
	sum += getVal(b, row+1, column+1)

	return sum
}

func getVal(b [][]int, row, column int) int {
	if row < 0 || row >= ROWS || column < 0 || column >= COLUMNS {
		return 0
	} else {
		return b[row][column]
	}
}

func print(b [][]int) {

	fmt.Println("length ", len(b))
	str := "------------------\n"
	for row := 0; row < ROWS; row++ {
		str = str + "|"
		for column := 0; column < COLUMNS; column++ {
			if b[row][column] == 0 {
				str = str + "."
			} else {
				str = str + "+"
			}

			if column == COLUMNS-1 {
				str = str + "|"
			}

		}
		str = str + "\n"

	}
	str += "---------------------\n"
	fmt.Printf(str)
}

func main() {
	board := createBoard()
	generate(board)
	print(board)
	for i := 0; i < 20; i++ {
		board = nextGeneration(board)
		print(board)
	}

}
