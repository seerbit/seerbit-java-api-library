
<div align="center">
 <img width="400" valign="top" src="https://assets.seerbitapi.com/images/icon.png">
</div>


<h1 align="center">
  <img width="40" valign="bottom" src="https://res.cloudinary.com/dcksdncso/image/upload/v1579682633/java_f2iyuf.png">
  seerbit-java-v1
</h1>

<h4 align="center">
  A Seerbit API Library for Java (Version 1)
</h4>

## Features

The Library supports all APIs under the following services:
* Payment via API (card and account)
* Disputes
* Refunds
* Transaction Status

## Getting Started

A full getting started guide for integrating SeerBit can be found at [getting started docs](https://doc.seerbit.com).

## Documentation

The documentation, installation guide, detailed description of the SeerBit API and all of its features is [available on the documentation website](https://doc.seerbit.com/api/library)


## Requirements

* Java 8 or higher
* Maven


## Installation

### Maven

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.seerbit</groupId>
  <artifactId>seerbit-java-api</artifactId>
  <version>1.0</version>
</dependency>
```

## Contributing

You can contribute to this repository so that anyone can benefit from it:

* Improved features
* Resolved bug fixes and issues

## Examples  

You can also check the [src/main/java/com/seerbit/demo](https://github.com/seerbit/seerbit-java-v1/tree/master/src/main/java/com/seerbit/demo) folder in this repository for more examples of usage.

## Using the Library

<strong><h4>Initiate Card Transaction</h4></strong>
Instantiate a client and set the parameters.

```java
    Seerbit seerbitApp = new SeerbitImpl();
    Client client = new Client();
    client.setAPIBase(seerbitApp.getApiBase());
    client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
    client.setPublicKey("public_key");
    client.setPrivateKey("private_key");
    client.setTimeout(20);
```

To initiate a transaction request you need to perform a transaction authentication. 

```java
    TransactionAuthenticationImpl authService = new TransactionAuthenticationImpl(client);
    JsonObject response = authService.doAuth();
```

Then retrieve your token after authenticating and pass it to the CardService constructor along with your client object. You can then construct your payload and call the <code>doAuthorize()</code> method of the CardService class.
```java
    String token = authService.getToken();
  
    CardService cardService = new CardServiceImpl(client, token);

    CardDetail cardDetail = new CardDetail();
    cardDetail.setCvv("100");
    cardDetail.setNumber("5123450000000008");
    cardDetail.setExpiryMonth("05");
    cardDetail.setExpiryYear("21");
    cardDetail.setPin("1234");

    Card card = new Card();
    card.setFullname("Sambo Chukwuma Adetutu");
    card.setPublicKey(client.getConfig().getPublicKey());
    card.setTransactionReference("trx00001");
    card.setEmail("sambo@example.com");
    card.setMobile("08012345678");
    card.setChannelType("account");
    card.setDeviceType("Nokia 3310");
    card.setSourceIP("127.0.0.20");
    card.setType("3DSECURE");
    card.setCurrency("NGN");
    card.setDescription("put a descriptive message here");
    card.setCountry("NG");
    card.setFee("1.00");
    card.setAmount("150.00");
    card.setClientAppCode("appl");
    card.setCallbackUrl("http://www.example.com");
    card.setRedirectUrl("http://www.example.com");
    card.setCardDetail(cardDetail);

    JsonObject json = cardService.doAuthorize(card);
``` 

Find more examples [here](https://github.com/seerbit/seerbit-java-v1/tree/master/src/main/java/com/seerbit/demo).

## Licence
GNU General Public License. For more information, see the LICENSE file.

## Website
* https://seerbit.com
