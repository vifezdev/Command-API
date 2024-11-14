# Vifez Command API
This is a small base of how a Command API would work, It contains an example command with an example of how registering commands would work in the main class, and obviously the code for the api

# Features:
- Easy Creation
- Simple Registration
- Permission Support
- Alias Handling
- Tab Completion
- Player-Only Support

# How to use:
- Clone the repository
- Run `mvn clean install` to compile and install
- Add the following dependency to your `pom.xml`
```xml
    <dependency>
        <groupId>lol.vifez.command</groupId>
        <artifactId>CommandAPI</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>compile</scope>
    </dependency>

```