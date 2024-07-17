Add SQLib as a dependency in your pom.xml:
```
<dependency>
    <groupId>io.github.pigaut.lib.sql</groupId>
    <artifactId>SQLib</artifactId>
    <version>1.1</version>
</dependency>
```

Shading with Maven Shade Plugin
``` 
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.6.0</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                    <configuration>
                        <relocations>
                            <relocation>
                                <pattern>io.github.pigaut.lib.sql</pattern>
                                <shadedPattern>YOUR_PACKAGE.shaded.sqlib</shadedPattern>
                            </relocation>
                        </relocations>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
Replace YOUR_PACKAGE with your own package namespace where SQLib will be shaded.
