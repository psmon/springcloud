# LOBBY API

## LobbyAPI PreView

[](/libary/doc-res/lobbyapi-preview.png)

APILIST:
- PUT /keepalive/{authToken}
- GET /zone/games/{playMode}/{gameType}/{buyin}/{limit}/{seats}?authToken={authToken}
- GET /cash/tables/{playMode}/{gameType}/{buyin}/{limit}/{seats}?authToken={authToken}
- GET/tournaments/games/{playMode}/{gameFormat}/{tournamentType}/{gameType}/{buyin}/{limit}/{seats}?authToken={authToken}
- GET /zone/buyin/{gameId}/{authToken}
- POST /zone/buyin/{gameId}/{authToken}
- GET /tournaments/{tournamentId}/register/{authToken}
- POST /tournaments/{tournamentId}/register/{authToken}
- POST /launchGame/{gameFormat}/{gameId}/{authToken}
- GET /tournaments/{tournamentId}/info
- GET /tournaments/{tournamentId}/state
- GET /tournaments/{tournamentId}/tables
- GET /tournaments/{tournamentId}/leaderboard
- GET /tournaments/{tournamentId}/rank/{authToken}
- POST /tournaments/{tournamentId}/unregister/{authToken}