# 테스트용 텍스트
- Prompt

```
[Instructions]
        - Write a diary entry in Korean following the instructions below, referring to the 'Response Format' and 'Consume List'
        - Be sure to follow the 'Response Format' and do not respond otherwise.
        - this is Consume_List format
            {
                today consumption limit : amount,
                today consumption details : {
                    consumer items : [amount,satisfaction(1~5)]
                    ...
                }
            }
        [Response Format]
        {
        "content": "like an 75-years-old, write a fun  diary(title over 10 characters + content over 50 characters)",
        "comment": "As an elementary school teacher, give comment",
        "stamp_type": "assign a score judging the spending details from a range of 1 to 5, int".
        }

[Consume_List]
{
		"ConsumptionLimit": 30000,
		"ConsumptionDetails": {
					"순대국밥": [9000, 3],
					"메가커피": [2000, 4],
		}
}

\nSpendingLimit:10000Won\n
```

- Response
```JSON
{
	"content": "주름진 손으로 맛있는 하루 (제목)\n\n오늘은 아침부터 햇살이 환하게 비치는 따스한 날이었다. 장보기도 하고, 약간의 산책도 했지만, 무엇보다도 오늘의 하이라이트는 맛있는 순대국밥이었다. 9000원을 지불하고 맛본 그 국밥은 세월의 흐름을 느끼게 하는, 그런 따뜻한 맛이었다. 아무래도 조금 실망스러웠던 것 같다. 그래도 3점 정도는 줄 수 있을 것 같다.\n\n그리고 오후에는 2000원에 메가커피를 즐겼다. 커피는 예전에 비해 많이 찾아간 듯하지만, 여전히 마시면 마시는대로 기분이 좋아져서 4점을 주고 싶다. 하루를 마무리하며 이렇게 다이어리를 쓰니, 마음이 편안해지는 기분이다.",
	
	"comment": "잘하셨습니다! 오늘의 소소한 행복과 실망을 잘 표현해주셨어요. 다만, 순대국밥에 대한 경험을 좀 더 디테일하게 기술해주시면 어땠을까 싶습니다. 그래도 어르신의 하루를 잘 느낄 수 있었어요. 수고하셨습니다!",
	
	"stamp_type": 4
}
```