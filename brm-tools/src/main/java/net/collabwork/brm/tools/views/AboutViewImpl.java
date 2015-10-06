package net.collabwork.brm.tools.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import net.collabwork.brm.tools.ui.resources.ImageBundle;
import net.miginfocom.swing.MigLayout;

import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.Timeline.TimelineState;
import org.pushingpixels.trident.callback.TimelineCallback;
import org.pushingpixels.trident.ease.Spline;
import org.springframework.stereotype.Component;

@Component
public class AboutViewImpl extends JDialog implements AboutView {

	private Timeline timeline;

	public AboutViewImpl() {
		setUndecorated(true);
		setOpacity(0.f);
		setPreferredSize(new Dimension(350, 130));
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("A propos");
		setAlwaysOnTop(true);
		setResizable(false);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new MigLayout("", "[100px][grow][10px]", "[center][center][]"));
		JLabel label = new JLabel(ImageBundle.ghostIcon());
		JButton btClose = new JButton(new AbstractAction("x") {
			@Override
			public void actionPerformed(ActionEvent e) {
				display(false);
			}
		});
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		getContentPane().setBackground(Color.decode("#2A2A2A"));
		getContentPane().add(label, "cell 0 0 1 2, align center");
		getContentPane().add(btClose, "cell 2 0");
		pack();

		timeline = new Timeline(this);
		timeline.setDuration(750);
		timeline.setEase(new Spline(0.0100f, 0.662f, 0.00f, 1.0f));
		timeline.addPropertyToInterpolate("opacity", 0.f, 1.f);
		timeline.addCallback(new TimelineCallback() {
			@Override
			public void onTimelineStateChanged(TimelineState oldState, TimelineState newState, float durationFraction,
					float timelinePosition) {

				if (newState == TimelineState.DONE && timelinePosition <= 0) {
					AboutViewImpl.this.setVisible(false);
				}
				if (newState == TimelineState.PLAYING_FORWARD && timelinePosition <= 0.0) {
					AboutViewImpl.this.setVisible(true);
				}
			}

			@Override
			public void onTimelinePulse(float durationFraction, float timelinePosition) {

			}
		});
	}

	@Override
	public void display(boolean visible) {
		if (visible) {
			timeline.play();
		} else {
			timeline.playReverse();
		}
	}
}
