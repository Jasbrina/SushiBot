# SushiBot
 

[logo]: https://i.imgur.com/U1fQ4D8.png "."
General-purpose artbot created for assisting in several Discord servers. Has a variety of commands, ranging from administrative to fun and art-esque. Hosted 24/7 with a 99% uptime.


## Invite to Server

[**Invite Link**](https://discordapp.com/api/oauth2/authorize?client_id=542497057567408137&permissions=0&scope=bot)


## Command List

SushiBot has an extensive list of commands. A full list of commands follow:

| HELP COMMANDS        | DESCRIPTION           | USAGE  |
| ------------- |-------------| :-----|
| Help      | Recieve information about other commands | -help |

| UTILITY COMMANDS        | DESCRIPTION           | USAGE  |
| ------------- |-------------| :-----|
| Server Information      | Display information about the current server | -server |
| User Information | Displays information about a self user or other user | -user @\<username> or -user for self|
| Avatar | Retrieve a high-resolution image of yours or someone else's profile picture. |  -avatar @\<username> or -avatar for self|
| Display Roles      | Brings up a list of user-assignable roles in the server. | -r |
| Add Role     | Adds a role to the user. Executes only if role is able to be assigned to that user. | -ra \<role name> |
| Remove Role      | Removes a role from the user. Executes only if role is able to be removed from that user. | -rr \<role name> |

| FUN COMMANDS        | DESCRIPTION           | USAGE  |
| ------------- |-------------| :-----|
| Minesweeper | Play a fun game of minesweeper! Made possible through Discord's use of spoiler markdown. Fully randomized. | -ms|
| Hug | Hug someone!| -hug @\<username>|
| League | Angry you lost a match? Use this!| -ihateleague or -league|
| Make a Ship | Create a ship between two people! Inspired by weeb.sh's Image Generation (korra)'s Loveship feature. |-l @\<username1> @\<username2> |
| Pay Respects| Press -f to pay respects. Literally. | -f|
| Ask | Ask the magic 8ball a question! | -ask \<question>|
| Lenny | ( ͡° ͜ʖ ͡°) | -lenny|

| ART COMMANDS        | DESCRIPTION           | USAGE  |
| ------------- |-------------| :-----|
| Pixiv      | Retrieves information from Pixiv.net to bring up images based on a keyword search or the daily rankings. Might bring up NSFW images. Takes a while to compute. | -pixiv \<searchterm> or -pixiv ranking |
| Search for Artist     | Prints a requested user's social media links. Linked to /r/AnimeSketch Discord's artist database. *(thus only recommended to be deployed in the /r/AnimeSketch Discord).* | -artist \<artist name> |


## Screenshots
Some examples of usage and features of SushiBot.

 ![image][1]
 
 ![image][3]
 
 ![image][4]
 
 ![image][5]
 
 ![image][6]

 ![image][7]
 
 ![image][8]
 
 ![image][9]
 
 ![image][10]
 
 ![image][11]

[1]: https://i.imgur.com/ot278lC.png "."
[2]: https://i.imgur.com/PHUTaCg.png "."
[3]: https://i.imgur.com/o0XCAiY.png "."
[4]: https://i.imgur.com/DdzQU3z.png "."
[5]: https://i.imgur.com/F0ngoBy.png "."
[6]: https://i.imgur.com/KqbgD6w.png "."
[7]: https://i.imgur.com/BPpd7w4.png "."
[8]: https://i.imgur.com/YcIRhqE.png "."
[9]: https://i.imgur.com/bwGQasj.png "."
[10]: https://i.imgur.com/Iq2U6YZ.png "."
[11]: https://i.imgur.com/i4HLjXO.png "."

### Key Development Challenges

* [http://www.pixiv.net]()'s images links only work if the referrer is from the pixiv site itself. This proved a challenge when grabbing images from the site. A workaround I came up with was using `HttpURLConnection` to set the referrer and user agent to come from pixiv.net. Then, the image is downloaded to my local machine and uploaded to the Discord server.

## Contact
* SushiHammer#4828 on Discord



## Acknowledgments

* Credits to all APIs used- further information in build.gradle file
* Thank you for reading!
