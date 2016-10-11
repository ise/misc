package main

import (
	"fmt"
	"encoding/json"
	"regexp"
	"github.com/golang/go/src/sort"
)

func main() {
	/*
	var input string
	scanner := bufio.NewScanner(os.Stdin)
	for scanner.Scan() {
		input += scanner.Text()
	}
	*/
	input := `{"key1": "value1", "key2": "value2"}`

	patterns := []string{`ObjectId\(.*?\)`, `NumberLong\(.*?\)`, `ISODate\(.*?\)`}
	for _, p := range patterns {
		rep := regexp.MustCompile(p)
		input = rep.ReplaceAllString(input, "\"rep\"")
	}
	jsonBlob := []byte(input)
	var jsonData interface{}
	_ = json.Unmarshal(jsonBlob, &jsonData)
	fmt.Printf("| field | type | 説明 | 備考 |\n")
	fmt.Printf("| --- | --- | ---| --- |\n")

	var keys []string
	for key := range jsonData.(map[string]interface{}) {
		keys = append(keys, key)
	}

	sort.Strings(keys)

	for _, key := range keys {
		fmt.Printf("| %v | | | |\n", key)
	}
}
