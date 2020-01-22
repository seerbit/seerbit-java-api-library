
<div align="center">
 <img width="200" valign="top" src="https://res.cloudinary.com/dy2dagugp/image/upload/v1571249658/seerbit-logo_mdinom.png">
</div>


<h1 align="center">
  <img width="40" valign="bottom" src="https://res.cloudinary.com/dcksdncso/image/upload/v1579682633/java_f2iyuf.png">
  seerbit-java-api-library
</h1>

<h4 align="center">
  A Seerbit API Library for Java (Version 1)
</h4>

## Features

The Library supports all APIs under the following services:
* Card API
* Refunds
* Account
* Disputes
* Transaction API

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
  <version>1.0.0</version>
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
    client.setTimeout(20);
    client.setAPIBase(seerbitApp.getApiBase());
    client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
    client.setPublicKey("public_key");
    client.setPrivateKey("private_key");
    client.setTimeout(20);
```

To initiate a transaction request you need to configure your client and perform a 
transaction authentication. 

```java
    TransactionAuthenticationImpl authService = new TransactionAuthenticationImpl(client);
    authService.doAuth();
```

Then retrieve your token after authenticating and pass it to the CardService constructor along with your client object. You can then construct your payload and call the <code>doAuthorize()</code> method of the CardService class.
```java
    String token = authService.getToken();
  
    CardService cardService = new CardServiceImpl(client, token);

    AccountDetail account = new AccountDetail();
    account.setName("Sambo Chukwuma Adetutu");
    account.setBvn("1234567890");
    account.setSender("1111111111");
    account.setSenderBankCode("214");
    account.setSenderDateOfBirth("04011984");

    CardDetail cardDetail = new CardDetail();
    cardDetail.setCvv("100");
    cardDetail.setNumber("5123450000000008");
    cardDetail.setExpirymonth("05");
    cardDetail.setExpiryyear("21");
    cardDetail.setPin("1234");

    Card cardPayload = new Card();
    cardPayload.setFullname("Sambo Chukwuma Adetutu");
    cardPayload.setPublicKey(client.getConfig().getPublicKey());
    cardPayload.setTransactionReference("trx00001");
    cardPayload.setEmail("sambo@example.com");
    cardPayload.setMobile("08012345678");
    cardPayload.setChannelType("account");
    cardPayload.setDeviceType("Nokia 3310");
    cardPayload.setSourceIP("127.0.0.20");
    cardPayload.setType("3DSECURE");
    cardPayload.setCurrency("NGN");
    cardPayload.setDescription("put a descriptive message here");
    cardPayload.setCountry("NG");
    cardPayload.setFee("1.00");
    cardPayload.setAmount("150.00");
    cardPayload.setClientAppCode("appl");
    cardPayload.setCallbackUrl("http://www.example.com");
    cardPayload.setRedirectUrl("http://www.example.com");
    cardPayload.setCard(cardDetail);
    JsonObject json = cardService.doAuthorize(cardPayload);
``` 

Find more examples [here](https://github.com/seerbit/seerbit-java-v1/tree/master/src/main/java/com/seerbit/demo).

## Licence
GNU General Public License. For more information, see the LICENSE file.

## Website
* https://seerbit.com
