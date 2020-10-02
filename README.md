
<div align="center">
 <img width="200" valign="top" src="https://res.cloudinary.com/dy2dagugp/image/upload/v1571249658/seerbit-logo_mdinom.png">
</div>


<h1 align="center">
  <img width="40" valign="bottom" src="https://res.cloudinary.com/dcksdncso/image/upload/v1579682633/java_f2iyuf.png">
  seerbit-java-v2
</h1>

<h4 align="center">
  A Seerbit API Library for Java (Version 2)
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
  <version>1.0.1</version>
</dependency>
```

## Contributing

You can contribute to this repository so that anyone can benefit from it:

* Improved features
* Resolved bug fixes and issues

## Examples  

You can also check the [src/main/java/com/seerbit/demo](https://github.com/seerbit/seerbit-java-api-library/tree/v2/src/main/java/com/seerbit/v2/demo) folder in this repository for more examples of usage.

## Using the Library

<strong><h4>Initiate Account Transaction</h4></strong>
Instantiate a client and set the parameters. Then perform service authentication by instantiating the authentication service object and passing the client to it in its constructor. Retrieve your token by calling the <code>getToken()</code> method.

```java
   Seerbit seerbit = new SeerbitImpl();
   client = new Client();
   client.setApiBase(seerbit.getApiBase());
   client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
   client.setPublicKey("public_key");
   client.setPrivateKey("private_key");
   client.setTimeout(20);
   AuthenticationService authService = new AuthenticationServiceImpl(client);
   JsonObject json = authService.doAuth();
   String jsonString = String.format("auth response: \n%s", json.toString());
   String token = authService.getToken();
   
```

After retrieving your token following authentication proceed to pass it to the AccountServiceImpl constructor along with your client object. You can then construct your payload and call the <code>doAuthorize()</code> method of the AccountServiceImpl class.


```java
 
   AccountService accountService = new AccountServiceImpl(client, token); // pass the client and token in the constructor
   Account account = new Account();
   account.setFullName("Musa Chukwuma Adetutu");
   account.setEmail("musa@example.com");
   account.setMobileNumber("08012345678");
   account.setPublicKey(client.getPublicKey());
   account.setChannelType("BANK_ACCOUNT");
   account.setDeviceType("nokia 33");
   account.setSourceIP("1.0.1.0");
   account.setPaymentReference("trx0001");
   account.setCurrency("NGN");
   account.setProductDescription("put a description here");
   account.setProductId("Foods");
   account.setCountry("NG");
   account.setFee("1.00");
   account.setAmount("100.00");
   account.setClientAppCode("app1");
   account.setRedirectUrl("http://www.example.com");
   account.setAccountName("Diei Okechukwu Peter");
   account.setAccountNumber("2213132677");
   account.setBankCode("057");
   account.setBvn("22912882998");
   account.setRetry("false");
   account.setInvoiceNumber("1234567891abc123ac");
   account.setDateOfBirth("01-01-2020");
   account.setPaymentType("ACCOUNT");
   JsonObject response = accountService.doAuthorize(account);
  
``` 

Find more examples [here](https://github.com/seerbit/seerbit-java-api-library/tree/v2/src/main/java/com/seerbit/v2/demo).

## Licence
GNU General Public License. For more information, see the LICENSE file.

## Website
* https://seerbit.com
