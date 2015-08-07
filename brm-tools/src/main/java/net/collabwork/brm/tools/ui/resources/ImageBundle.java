package net.collabwork.brm.tools.ui.resources;

import javax.swing.ImageIcon;

public class ImageBundle {
	public static ImageIcon accept() {
		return new ImageIcon(ImageBundle.class.getResource("accept.png"));
	}

	public static ImageIcon add() {
		return new ImageIcon(ImageBundle.class.getResource("/net/collabwork/brm/tools/ui/resources/add.png"));
	}

	public static ImageIcon remove() {
		return new ImageIcon(ImageBundle.class.getResource("cross.png"));
	}

	public static ImageIcon edit() {
		return new ImageIcon(ImageBundle.class.getResource("pencil.png"));
	}

	public static ImageIcon save() {
		return new ImageIcon(ImageBundle.class.getResource("disk.png"));
	}

	public static ImageIcon punchManagementIcon() {
		return new ImageIcon(ImageBundle.class.getResource("cog_go.png"));
	}
}
