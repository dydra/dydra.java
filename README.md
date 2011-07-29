Dydra.com Software Development Kit (SDK) for Java
=================================================

This is the official Java software development kit (SDK) for [Dydra.com][],
the cloud-hosted RDF & SPARQL database service.

<https://github.com/dydra/dydra-java>

Examples
--------

See [`doc/examples/raw-jena`][raw-jena] for examples on how to query Dydra's
standard APIs using [Jena][]/[ARQ][], and [`doc/examples/sdk-jena`][sdk-jena]
for simplified versions of the same based on the convenience wrappers
provided as part of this SDK.

Dependencies
------------

* [Java](http://java.com/) (>= 1.6.0)

Note: the instructions in this README, and the operation of the library
itself, implicitly assume a Unix system (Mac OS X, Linux, or *BSD) at
present. Patches improving Windows support are most welcome.

Download
--------

To get a local working copy of the development repository, do:

    $ git clone git://github.com/dydra/dydra-java.git

Mailing List
------------

* <http://groups.google.com/group/dydra>

Authors
-------

* [Arto Bendiken](https://github.com/bendiken) - <http://dydra.com/bendiken>

Contributing
------------

* Do your best to adhere to the existing coding conventions and idioms.
* Don't use hard tabs, and don't leave trailing whitespace on any line.
* Don't touch the `VERSION` or `AUTHORS` files. If you need to change them,
  do so on your private branch only.
* Do feel free to add yourself to the `CREDITS` file and the corresponding
  list in the `README`. Alphabetical order applies.

License
-------

This is free and unencumbered public domain software. For more information,
see <http://unlicense.org/> or the accompanying `UNLICENSE` file.

[Java]:       http://java.com/
[Maven]:      http://maven.apache.org/
[Buildr]:     http://buildr.apache.org/
[RDF]:        http://www.w3.org/RDF/
[PDD]:        http://unlicense.org/#unlicensing-contributions
[Dydra.com]:  http://dydra.com/
[raw-jena]:   https://github.com/dydra/dydra-java/tree/master/doc/examples/raw-jena
[sdk-jena]:   https://github.com/dydra/dydra-java/tree/master/doc/examples/sdk-jena
[Jena]:       http://jena.sourceforge.net/
[ARQ]:        http://jena.sourceforge.net/ARQ/
