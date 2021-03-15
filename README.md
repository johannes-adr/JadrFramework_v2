# JadrFramework_v2
Simple pure java game framework


## Maven

### Jitpack repository
```XML
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

### Dependency
```XML
<dependency>
	<groupId>com.github.johannes-adr</groupId>
	<artifactId>JadrFramework_v2</artifactId>
	<version>CHANGE_VERSION</version>
</dependency>
```
replace CHANGE_VERSION with:
[![](https://jitpack.io/v/johannes-adr/JadrFramework_v2.svg)](https://jitpack.io/#johannes-adr/JadrFramework_v2)

## Example

```Java
package de.jadr;

import java.awt.Graphics2D;

import de.jadr.assets.Textures;
import de.jadr.world.Startworld;
import de.jadr.world.TileSet;
import de.jadr.world.TileSet.TileElement;
import de.j2d.simple.JadrGame;

public class Main extends JadrGame implements Runnable {


	
	public static void main(String[] args) {
		new Main("RPG", 1200, 800);
	}
	
	private Element p;
	public Main(String title, int width, int height) {
		super(title, width, height);
		
		p = new Element(200, 200, 64, 64);
		getGameCamera().foc = p;
		addElement(p, true);
		
		super.start();
	}

	@Override
	protected void onPostRender(Graphics2D g) {

	}

	@Override
	protected void onPostUpdate() {

	}

	@Override
	protected void onPreRender(Graphics2D g) {
		
	}

}
```
