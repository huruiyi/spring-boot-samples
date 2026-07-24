# How to use this library.

This library should work hand-in-hand with openssl. With that said, here is how to use this library.

- Within your terminal (Unix based OS) type the following.

```
openssl genrsa -out rsa_1024_priv.pem 1024
```

- This generates a private key, which you can see by doing the following...

```
cat rsa_1024_priv.pem
```

- You can then copy and paste this in the Private Key section of within index.html.
- Next, you can then get the public key by executing the following command.

```
openssl rsa -pubout -in rsa_1024_priv.pem -out rsa_1024_pub.pem
```

- You can see the public key by typing...

```
cat rsa_1024_pub.pem
```

- Now copy and paste this in the Public key within the index.html.
- Now you can then convert to and from encrypted text by doing the following in code.

[Win64-OpenSSL下载](https://slproweb.com/products/Win32OpenSSL.html) [Linux-OpenSSL下载](https://openssl-library.org/source/)

