# 테스트용 텍스트
"""
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
"""