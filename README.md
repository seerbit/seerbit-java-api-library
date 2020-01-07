
<div align="center">
 <img width="200" valign="top" src="https://res.cloudinary.com/dy2dagugp/image/upload/v1571249658/seerbit-logo_mdinom.png">
</div>


<h1 align="center">
  <img width="40" valign="bottom" src="file:///Users/centricgateway/Downloads/java.png">
  seerbit-java-api
</h1>

<h4 align="center">
  A Seerbit API Library for Java
</h4>

## Features

The Library supports all APIs under the following services:
* Payments
* Webhooks
* Card API
* Refunds
* Account
* Disputes
* Transaction API

## Getting Started

A full getting started guide for integrating SeerBit can be found at [getting started docs](https://doc.seerbit.com).

## Documentation

The documentation, installation guide, detailed description of the SeerBit API and all of its features is [available on the documentation website](https://doc.seerbit.com/api/library).


## Requirements

* Java 8 or higher
* Maven


## Installation

### Download JAR

You can use Maven or just download the release.
* Download seerbit-java-api JAR file
* Add the JAR file as a module to your Java project:
* On Intellij IDEA: File -> Project Structure -> Modules -> Dependencies Tab -> Add -> JARs or Directories -> Attach JAR
* On Netbeans: Project properties -> Libraries -> Compile -> ADD JAR/folder -> Add JAR

### Maven

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

You can also check the [src/main/java/com/seerbit/demo](https://github.com/seerbit/seerbit-java-api/tree/master/src/main/java/com/seerbit/demo) folder in this repository for more examples of usage.

## Using the Library

Instantiate a client and set the environment.

<strong>For Pilot:</strong>

```java
    Client client = new Client();
    client.setEnvironment(EnvironmentEnum.PILOT.getEnvironment());
```

<strong>For Live:</strong>

```java
    Client client = new Client();
    client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
```

## Code Samples 

<strong><h4>Initiate Card Transaction</h4></strong>
To initiate a transaction request you need to configure your client and perform a 
transaction authentication. 

```java
    Seerbit seerbitApp = new SeerbitImpl();
    Client client = new Client();

    client.setTimeout(20);
    client.setAPIBase(seerbitApp.getApiBase());
    client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
    client.setPublicKey("YOUR_PUBLIC_KEY");
    client.setPrivateKey("YOUR_SECRET_KEY");
    client.setTimeout(20);

    TransactionAuthentication authService = new TransactionAuthentication(client);
    JsonObject json = authService.doAuth();
```

Then retrieve your token after authenticating and pass it to the CardService constructor along with your client object. You can then construct your payload and call the <code>doAuthorize()</code> method of the CardService.
```java
    String token = authService.getToken();
    CardService cardService = new CardService(client, token);   
    Map<String, Object> cardPayload = new HashMap<>();
    Account account = new Account();
    JsonObject json = new JsonObject();
    Card card = new Card();

    account.setName("AYODELE PRAISE EREMA");
    account.setBvn("22141741835");
    account.setSender("0038721434");
    account.setSenderBankCode("214");
    account.setSenderDateOfBirth("04011984");

    card.setCvv("100");
    card.setNumber("5061040201593455366");
    card.setExpirymonth("05");
    card.setExpiryyear("21");
    card.setPin("8319");

    cardPayload.put("fullname", "Aminu Grod");
    cardPayload.put("public_key", client.getConfig().getPublicKey());
    cardPayload.put("tranref", "TQ14611X32131PR");
    cardPayload.put("email", "kolawolesam@gmail.com");
    cardPayload.put("mobile", "080305206221");
    cardPayload.put("channelType", "ACCOUNT");
    cardPayload.put("deviceType", "Nokia 3310");
    cardPayload.put("sourceIP", "127.0.0.20");
    cardPayload.put("type", "3DSECURE");
    cardPayload.put("currency", "NGN");
    cardPayload.put("description", "Pilot Test Account");
    cardPayload.put("country", "NG");
    cardPayload.put("fee", "1.00");
    cardPayload.put("amount", "150.00");
    cardPayload.put("clientappcode", "appl");
    cardPayload.put("callbackurl", "http://url.com");
    cardPayload.put("redirecturl", "http://url.com");
    cardPayload.put("account", account);
    cardPayload.put("card", card);

    json = cardService.doAuthorize(cardPayload);
``` 
<strong><h4>Validate OTP</h4></strong>
To validate transaction initiated with card that uses OTP e.g (Verve) you need to configure your client and 
perform a transaction authentication as stated previously (see above). 

Instantiate a <code>HashMap</code> and <code>Transaction</code> object for the otpPayload and transaction. Call 
the <code>setOtp()</code> method and the <code>setLinkingReference()</code> method of the transaction object. 
Add the transaction object to the otpPayload map.

```java
    Map<String, Object> otpPayload = new HashMap<>();
    Transaction transaction = new Transaction();
    
    transaction.setOtp("458504");
    transaction.setLinkingReference("F771181731576846159311");
    
    otpPayload.put("transaction", transaction);
    
    json = cardService.doValidateOTP(otpPayload);
```

## Licence
GNU General Public License. For more information, see the LICENSE file.

## Website
* https://seerbit.com
