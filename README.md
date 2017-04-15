# play-cms

## Running
To run this example locally do the following from the command line:

sbt "run 9001"

Then in a web browser go to http://localhost:9001

# How it works
# Request
A CMS Frontend (FE) client requests a page from the CMS, this request is as follows:

http://localhost:9001/somesite/page/home

Breakdown of request:

Server (including protocol and port): http://localhost:9001
Request Group: /somesite
Request Type: /page
Unique page identifier: /home

A CMS can host many groups, each group includes configuration that will be passed back to the CMS FE client.

Many pages can be configured for each group.

# Response
For this request the server returns a JSON document of the following structure:

{
    group: {
        name: "Some Site"
    },
    template : {
        name: "template-home"
    }
    page: {
        name: "home"
        content: "This is the home page"
    }
    navigation: {
        links: [
            {
                text: "Home",
                page: "home",
                altText: "Home page"
            }
        ]
    }
}

# Building
sbt scalastyle
sbt compile

# CMS Codes
The CMS allows substitution of certain pieces of information, below are a list of code that can be used
in templates that will automatically be replaced with the data specified:

{serverAssets}      A URL built from the protocol, server, port and groupName to enable assets from the CMS Server to be provided to the pressed pages.
{groupTitle}        The groupTitle, aka the nice name of the group.
