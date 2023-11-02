# message-broker
## Description
Use SpringBoot and mongodb to preserve messages from LINE.</br>
This application uses [line-bot-sdk-java](https://github.com/line/line-bot-sdk-java) version 7.x.

## Requirements
- This application requires Java 17 or later.
- File `src\main\resources\application.properties` needs to be modified before running this application.

  - `spring.data.mongodb.host`: IP address or DNS 
  - `spring.data.mongodb.database`: Collection name of mongodb
  - `spring.data.mongodb.username`: Username to sign in mongodb
  - `spring.data.mongodb.password`: Password to sign in mongodb
  - `spring.data.mongodb.port`: Port number of mongodb
  - `line.bot.channel-token`: Channel access token from Line Messaging API
  - `line.bot.channel-secret`: Channel secret from in Basic setting of Line Channel
  - `line.bot.handler.path`: Not required to change, using `/callback` by default

## API
### Send message to line
```http request
POST http://{{hostname}}:{{port}}/sendMessage
Content-Type: text/plain
```
### List all users
```http request
GET http://{{hostname}}:{{port}}/list/users
```
### List all messages
```http request
GET http://{{hostname}}:{{port}}/list/historyMessage
```
### Find messages by user
```http request
GET http://{{hostname}}:{{port}}/message/:userId
userId: String, the id given by Line.
```
- For example: If I want to find all messages from user 'U123', the URL should be `http://{{hostname}}:{{port}}/message/U123`
```