# 🦉Lingo
- [Description](#Description)
- [Functions ― based on API](#Functions---based-on-API)
  - [🌏 Translation](#-translation)
  - [🔮 Suggestion](#-suggestion)
  - [🎞️ Logs](#-logs)
- [Others](#Others)

## Description
> **Lingo is medical terminology-focused translation model testing environment.**
- Lingo provide a simple functions to test translation models.
- Lingo provide a basic translation model for testing.
- Lingo supports universal api to test multiple models.
- [Developed by ipalab(KNU)](https://sites.google.com/view/ipalab)
  - **Backend** : [Kim Dayeong](https://github.com/rlaekdud)
  - **Frontend** : [Kim Daegun]()
  - **Artificial Intelligence** : [Kim Byunghyun](), [Go Dongyeon]()

## Functions - based on API
### 🌏 Translation
> Get translation result from model with given query.
- URI : `/translation`
- Method : `GET`
- Request body :
    ```
    {
        service: "string", // model
        query: "string", // text to translate
        sourceLan: "string",
        targetLan: "string",
        kargs: dict
    }
    ```
- Response body :
    ```
    {
        score: dict,
        translateResult: "string"
    }
    ```
- Success response status : `200`
- Fail response status : `400`
- With authorization : `false`

### 🔮 Suggestion
> Get suggestion for given word, context.
- URI : `/suggestion`
- Method : `GET`
- Request body :
    ```
    {
        model: "string", // suggestion model
        targetWord: integer // Indexing by spacing
        sentence: boolean // Whether to look at the context as a sentence basis
        cntxt_len: integer // How long are you going to look at the context? e.g. 1:Front and back sentences
        text: "string" // translated total text
        abbreviation: boolean // Whether to change the abbreviation to its original meaning
    }
    ```
- Response body :
    ```
    {
        suggestions: ["string", ... ]
    }
    ```
- Success response status : `200`
- Fail response status : `400`
- With authorization : `false`

### 🎞 Logs
> Save logs of current session.
- URI : `/log`
- Method : `POST`
- Request body :
    ```
    {
        session: [
            {
                translationModel: "string",
                targetText: "string",
                sourceLan: "string",
                targetLan: "string",
                translatedText: "string",
                evaluation: {
                    job: "string",
                    grade: double,
                    feedback: "string"
                },
                suggestions: [
                    {
                        suggestionModel: "string",
                        targetWord: integer,
                        sentence: boolean,
                        cntxt_len: integer,
                        text: "string",
                        abbreviation: boolean,
                        suggestionList: ["string", ... ],
                        selectedSuggestion: "string"
                    }, ...
                ]
            }, ...
        ]
    }
    ```
- Response body :
    ```
    {
        message: "string"
    }
    ```
- Success response status : `200`
- Fail response status : `400`
- With authorization : `false`

## Others
- **This program is :**
  - only for testing purposes.
  - does not support membership functions.
- Use NoSQL database(MongoDB) for logs.
- Use Docker for deployment.