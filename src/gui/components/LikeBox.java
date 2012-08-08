package gui.components;

import java.awt.Component;
import java.net.URL;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;


public class LikeBox extends JComboBox{
	
	public static final String LIKE = "like";
	public static final String DONTLIKE = "notlike";
	public static final String MAYBE = "maybe";

	public LikeBox() {
		super(new Object[] {
				LIKE, MAYBE, DONTLIKE});
		this.setRenderer(new LikeBoxCellRenderer());
	}
	
	private final class LikeBoxCellRenderer extends DefaultListCellRenderer{

		private final ImageIcon thumbUp;
		private final ImageIcon thumbDown;
		private final ImageIcon thumbSide;
		
		public LikeBoxCellRenderer(){
			final URL thumpUpImageURL = LikeBoxCellRenderer.class.getResource("/resources/thumb_up.png");
			thumbUp = new ImageIcon(thumpUpImageURL);
			
			final URL thumpDownImageURL = LikeBoxCellRenderer.class.getResource("/resources/thumb_down.png");
			thumbDown = new ImageIcon(thumpDownImageURL);
			
			final URL thumpSideImageURL = LikeBoxCellRenderer.class.getResource("/resources/thumb_side.png");
			thumbSide = new ImageIcon(thumpSideImageURL);
		}
		 
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			final JLabel lbl = new JLabel();
			lbl.setBorder(new EmptyBorder(5,5,5,5));
			if(value instanceof String){
				final String val = (String)value;
				if(val.equals(LikeBox.LIKE)){
					lbl.setIcon(thumbUp);
				}else if(val.equals(LikeBox.DONTLIKE)){
					lbl.setIcon(thumbDown);
				}else{
					lbl.setIcon(thumbSide);
				}
			}

			return lbl; 
		}
		
	}
}
