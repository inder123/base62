# MD5 to Base62

An library providing methods to convert an MD5 value to a shorter Base 62 string

Examples:
```
Md5ToBase62.toBase62("9e107d9d372bb6826bd81d3542a419d6") ==>  cbIKGiMVkLFTeenAa5kgO4
Md5ToBase62.fromBase62("4KfZYA1udiGCjCEFC0l") ==>  0000bdd3bb56865852a632deadbc62fc
```
Note that fromBase62 method appends leading zeros to ensure it is a valid 32 character MD5 string.

The conversion is two-way, so you will get the original md5 back if you convert it back to md5:

```
Md5ToBase62.fromBase62(Md5ToBase62.toBase62("9e107d9d372bb6826bd81d3542a419d6"))  ==> 9e107d9d372bb6826bd81d3542a419d6
Md5ToBase62.toBase62(Md5ToBase62.fromBase62("cbIKGiMVkLFTeenAa5kgO4")) . ==> cbIKGiMVkLFTeenAa5kgO4
```

# Use with Gradle
add to your repositories
    ```
    repositories {
        maven { url "https://jitpack.io" }
    }
    ```
In your app build.gradle, add:  ```compile "com.github.inder123:base62:1.0.0"```
