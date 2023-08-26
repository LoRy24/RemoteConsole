# RemoteConsole
A plugin that permits to execute commands in a Minecraft network by sending a Secure HTTPS request to a server.

## Protocol Guide
The requests send as response a JSON data that contains multiple pieces of information. Here you can see more infos about that.

### Info
**Page: .../** (root)

When a request is sent to the root of the server, it will send the infos of this backend server. Here is an example:
```json
{
  "type": "proxy"
}
```
#### Explaination:
- The type parameter contains the type of the server that has started the backend feature. It can be **proxy** or **spigot**

### Execute 

**Page: .../execute**
The execute context is used to send a command that should be performed in the console. The request must contain the following
data:
- key -> The password used to authenticate the request. Is set in the config of the backend server
- server -> If sent to a proxy backend, should contain the server name of the server. Empty if it has to be executed directly on the Proxy
- command -> The command that should be executed (without the '/' character)

Example request:
```json
{
  "key": "mykey",
  "server": "",
  "command": "lp user LoRy24TV parent set owner"
}
```

Here is an example of the response
```json
{
  "status": 0
}
```

#### Explaination
- The status is the exit code, if 0 everything is ok, if -1 there was an error with the request body and -2 if the key is not valid