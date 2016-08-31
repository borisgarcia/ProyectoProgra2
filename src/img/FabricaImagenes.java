package img;

import java.net.URL;

import javax.swing.ImageIcon;

public class FabricaImagenes {

	public static ImageIcon getImageIcon(String rutaImagen) {
		ImageIcon imageIcon;
		URL resource = FabricaImagenes.class.getResource(rutaImagen);
		imageIcon = new ImageIcon(resource);
		return imageIcon;
	}

}
