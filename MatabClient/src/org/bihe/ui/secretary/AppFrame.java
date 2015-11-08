package org.bihe.ui.secretary;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolTip;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicTreeUI;

import org.arso.calendar.FarsiCalendar;

import com.miginfocom.ashape.AShapeUtil;
import com.miginfocom.ashape.Visibility;
import com.miginfocom.ashape.interaction.InteractionEvent;
import com.miginfocom.ashape.interaction.InteractionListener;
import com.miginfocom.ashape.interaction.MouseKeyInteractor;
import com.miginfocom.ashape.interaction.OverrideFilter;
import com.miginfocom.ashape.shapes.AShape;
import com.miginfocom.ashape.shapes.DrawAShape;
import com.miginfocom.ashape.shapes.FillAShape;
import com.miginfocom.ashape.shapes.TextAShape;
import com.miginfocom.beans.ActivityGridLayoutBean;
import com.miginfocom.beans.CategoryTreeBean;
import com.miginfocom.beans.DateAreaBean;
import com.miginfocom.calendar.activity.Activity;
import com.miginfocom.calendar.activity.ActivityDepository;
import com.miginfocom.calendar.activity.ActivityInteractor;
import com.miginfocom.calendar.activity.DefaultActivity;
import com.miginfocom.calendar.activity.view.ActivityView;
import com.miginfocom.calendar.category.Categorizable;
import com.miginfocom.calendar.category.Category;
import com.miginfocom.calendar.category.CategoryDepository;
import com.miginfocom.calendar.category.CategoryFilter;
import com.miginfocom.calendar.category.CategoryStructureEvent;
import com.miginfocom.calendar.datearea.DefaultDateArea;
import com.miginfocom.calendar.decorators.GridCellRangeDecorator;
import com.miginfocom.calendar.grid.Grid;
import com.miginfocom.calendar.grid.GridRow;
import com.miginfocom.util.MigUtil;
import com.miginfocom.util.dates.DateChangeEvent;
import com.miginfocom.util.dates.DateRange;
import com.miginfocom.util.dates.DateRangeI;
import com.miginfocom.util.dates.ImmutableDateRange;
import com.miginfocom.util.dates.MutableDateRange;
import com.miginfocom.util.dates.TimeSpanListEvent;
import com.miginfocom.util.filter.AndFilter;
import com.miginfocom.util.filter.Filter;
import com.miginfocom.util.gfx.GfxUtil;
import com.miginfocom.util.gfx.geometry.AbsRect;
import com.miginfocom.util.gfx.geometry.PlaceRect;
import com.miginfocom.util.gfx.geometry.numbers.AtEnd;
import com.miginfocom.util.gfx.geometry.numbers.AtFraction;
import com.miginfocom.util.gfx.geometry.numbers.AtStart;
import com.miginfocom.util.states.GenericStates;
import com.miginfocom.util.states.StatesI;
import com.miginfocom.util.states.ToolTipProvider;

/**
 * A demo of how to create a calendar that looks like iCal on the Mac.
 * <p>
 * The is a project for NetBeans that can be used directly.
 */

public class AppFrame extends javax.swing.JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addActivity(GregorianCalendar start, GregorianCalendar end,
			String summery, int id) {
		DateRange genRange2 = new DateRange(start.getTimeInMillis(),
				TimeZone.getDefault(), Locale.getDefault());
		genRange2.setEndMillis(end.getTimeInMillis(), false);

		long startMillis2 = genRange2.getStartMillis();
		long endMillis2 = genRange2.getEndMillis();
		ImmutableDateRange dr2 = new ImmutableDateRange(startMillis2,
				endMillis2, false, null, null);
		Activity activity2 = new DefaultActivity(dr2, new Integer(id));
		activity2.setSummary(summery);

		activity2.addCategoryID(homeID, -1);
		ActivityDepository.getInstance(
				dayDateArea.getActivityDepositoryContext()).addBrokedActivity(
				activity2, this, TimeSpanListEvent.ADDED);

		// ActivityDepository.getInstance().addBrokedActivity(activity2, null);
	}

	public void addActivity(long start, long end, String summery, int id) {
		DateRange genRange2 = new DateRange(start, TimeZone.getDefault(),
				Locale.getDefault());
		genRange2.setEndMillis(end, false);

		long startMillis2 = genRange2.getStartMillis();
		long endMillis2 = genRange2.getEndMillis();
		ImmutableDateRange dr2 = new ImmutableDateRange(startMillis2,
				endMillis2, false, null, null);

		Activity activity2 = new DefaultActivity(dr2, new Integer(id));
		activity2.setSummary(summery);

		activity2.addCategoryID(homeID, -1);
		ActivityDepository.getInstance(
				dayDateArea.getActivityDepositoryContext()).addBrokedActivity(
				activity2, this, TimeSpanListEvent.ADDED);
	}

	@SuppressWarnings("unused")
	private static final String[] TITLES = { "Going to the Gym",
			"Meeting with the Board", "Taking Mick to Ice Hockey",
			"Lunch with Susanne", "Lunch with Matt", "Meeting about the Site",
			"Template work", "Fix the car", "Take the car to the shop",
			"Call Chris about the fishing trip", "Major cleaning",
			"Refactoring some code" };

	@SuppressWarnings("unused")
	private static final String[] DESCRIPTIONS = { "This is a standard description of the event. It conveys details and notes and can be any text." };

	private static final Object calendarsID = 9;
	private static final Object homeID = 10;
	private static final Object workID = 11;
	private static final Object michaelID = 12;
	private static final Object gregID = 13;

	@SuppressWarnings("unused")
	private static final Object[] CATEGORIES = { homeID, workID, michaelID,
			gregID };

	private transient DateAreaBean currentDateArea;

	private ActivityGridLayoutBean activityGridLayoutBean = new ActivityGridLayoutBean();

	@SuppressWarnings("unused")
	private transient DefaultActivity newCreatedAct = null;

	public AppFrame() {
		setTitle("تقویم");

		configureCategories();
		configureActivities();

		initComponents();
		configureComponents();

		((JComponent) getContentPane()).setOpaque(true);
		((JComponent) getContentPane()).setBackground(Color.WHITE);

		// setSize(get);
		// setBounds(0, 0, 600, 600);
		// setLocationRelativeTo(null);
		overviewDateArea.getDateArea().setSelectedRange(
				new DateRange(System.currentTimeMillis(),
						DateRangeI.RANGE_TYPE_WEEK, 1, null, null));

		weekButton.doClick();

		// Run on the EDT so that the sizes are figured out already
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				overviewDateArea.scrollToShowRange(new DateRange(), 5.0f, 6.3f);

				MutableDateRange dr = dayDateArea.getDateArea()
						.getVisibleDateRange().getDateRangeClone();
				dr.setStartField(Calendar.HOUR_OF_DAY, 0);
				dr.setStartField(Calendar.MINUTE, 0);

				dayDateArea.scrollToShowRange(dr, 0.0f, 2.6f);
			}
		});

		currentDateArea.setDragStartDistance(10);
	}

	@SuppressWarnings("unused")
	private void configureComponents() {
		currentDateArea = dayDateArea;
		// Add the left square that shows the category color
		activityMonthAShape.addSubShape(createRecurrenceShape());
		activityDayAShape.addSubShape(createRecurrenceShape());
		activityDayAShape.addSubShape(createEndTimeShape());

		// Set the filter that only shows the activities that have cateories
		// that are checked in the tree.
		// monthDateArea.getDateArea().setActivityViewFilter(
		// new CatCheckedFilter());
		dayDateArea.getDateArea()
				.setActivityViewFilter(
						new AndFilter(new CatCheckedFilter(),
								new DayActsFilter(false)));
		topDayArea.getDateArea().setActivityViewFilter(
				new AndFilter(new CatCheckedFilter(), new DayActsFilter(true)));

		// activityGridLayoutBean is new to v6.5 and is a layout that sizes the
		// hight of the top whole day panel to the content.
		activityGridLayoutBean.setActivitySizeFirst(100);
		activityGridLayoutBean.setActivitySize(17);
		activityGridLayoutBean.setRowPadding(5);
		activityGridLayoutBean.setMinimumRowSize(30);
		activityGridLayoutBean.setRoundActivityTo(DateRangeI.RANGE_TYPE_DAY);
		topDayArea.setSecondaryDimensionLayout(activityGridLayoutBean);

		// Makes drag-create snappier
		// dayDateArea.getDateArea().setActivityLayoutDelay(0);
		// monthDateArea.setShowNoFitIcon(true);

		catTree.setRootCategoryId(calendarsID);
		((BasicTreeUI) catTree.getTree().getUI()).setRightChildIndent(3);

		// A click in the Category Tree
		catTree.addInteractionListener(new InteractionListener() {
			public void interactionOccured(InteractionEvent e) {
				Object o = e.getSource();
				if (o instanceof MouseKeyInteractor) {

				}

			}
		});

		// Shows how to add a right click popup menu
		catTree.addInteractionListener(new InteractionListener() {
			public void interactionOccured(InteractionEvent e) {
				if (CategoryTreeBean.LABEL_CLICKED.equals(e.getCommand()
						.getValue())) {
					MouseEvent me = (MouseEvent) e.getSourceEvent();
					if (me.isPopupTrigger()) {
						JPopupMenu pop = new JPopupMenu();
						pop.add(new JMenuItem("This is a popup"));
						pop.show(catTree, me.getX(), me.getY());
					}
				}
			}
		});

		// Add a tooltip provider that is much more configurable than for a
		// normal Swing component
		ToolTipProvider myTTP = new ToolTipProvider() {
			public int configureToolTip(JToolTip toolTip, MouseEvent e,
					Object source) {
				if (e.getID() == MouseEvent.MOUSE_MOVED
						&& source instanceof ActivityView) {
					toolTip.setForeground(Color.DARK_GRAY);
					String summary = ((ActivityView) source).getModel()
							.getSummary();
					if (summary != null)
						toolTip.setTipText("<html>"
								+ (summary.length() > 100 ? "<table width=300>"
										: "") + summary);

					return ToolTipProvider.SHOW_TOOLTIP;
				} else {
					return ToolTipProvider.HIDE_TOOLTIP;
				}
			}

			public int getPositionAdjustY() {
				return 23;
			}
		};

		// The code that makes it possible to move the activities between
		// categories.
		// dayDateArea.getDateArea().addActivityMoveListener(
		// new ActivityMoveListener() {
		// public void activityMoved(ActivityMoveEvent e) {
		// DefaultDateArea da = (DefaultDateArea) e.getSource();
		// Point p = ((MouseEvent) e.getEvent()).getPoint();
		//
		// Object[] oldCatArr = e.getActivity().getCategoryIDs();
		// Category oldCat = (oldCatArr != null && oldCatArr.length > 0) ?
		// CategoryDepository
		// .getCategory(oldCatArr[0]) : null;
		//
		// Category[] newCatArr = getCategoriesForPoint(da, p);
		// Category newCat = newCatArr.length > 0 ? newCatArr[0]
		// : null;
		//
		// if (oldCat != newCat && oldCat != null
		// && newCat != null) {
		// e.getActivity().setCategoryIDs(
		// new Object[] { newCat.getId() });
		// da.doLayout();
		// }
		// }
		// });

		// This code makes the normal activities in the month date area move one
		// full day and not change time within the day.
		// monthDateArea.getDateArea().addActivityMoveListener(
		// new ActivityMoveListener() {
		// public void activityMoved(ActivityMoveEvent e) {
		// ImmutableDateRange oldDR = e.getActivity()
		// .getBaseDateRange();
		// MutableDateRange newDR = e.getNewRange();
		//
		// newDR.setToRange(oldDR);
		//
		// Rectangle hitPoint = new Rectangle(((MouseEvent) e
		// .getEvent()).getPoint());
		// MutableDateRange hitRange = monthDateArea.getDateArea()
		// .getDateGrid().getDateRangeForRect(hitPoint);
		// if (hitRange == null)
		// return;
		//
		// Calendar newStart = hitRange.getStart();
		//
		// if (DateUtil.isInSame(oldDR.getStart(), newStart,
		// DateRangeI.RANGE_TYPE_DAY) == false) {
		// int year = newStart.get(Calendar.YEAR);
		// int day = newStart.get(Calendar.DAY_OF_YEAR);
		// long spanMillis = oldDR.getMillisSpanned(true,
		// false);
		// newDR.setToRange(oldDR);
		// newDR.setStartField(Calendar.YEAR, year);
		// newDR.setStartField(Calendar.DAY_OF_YEAR, day);
		// newDR.setMillisSpanned(spanMillis, true, false);
		// }
		// }
		// });

		// Paint the first 16 rows with gray
		Color rowColor = new Color(242, 242, 242);
		DefaultDateArea dayDA = dayDateArea.getDateArea();

		AbsRect cells = new AbsRect(AtStart.START0, AtStart.START0, AtEnd.END0,
				new AtStart(80));
		dayDA.addDecorator(new GridCellRangeDecorator(dayDA, 20, cells,
				rowColor, Grid.SIZE_MODE_INSIDE, false));

		// Paint the last 12 rows with gray
		cells = new AbsRect(AtStart.START0, new AtEnd(-10), AtEnd.END0,
				AtEnd.END0);

		dayDA.addDecorator(new GridCellRangeDecorator(dayDA, 20, cells,
				rowColor, Grid.SIZE_MODE_INSIDE, false));

		// Add popup menu to activities
		// dayDateArea.addInteractionListener(new
		// ActivityInteractionListener());
		// topDayArea.addInteractionListener(new ActivityInteractionListener());
		// monthDateArea.addInteractionListener(new
		// ActivityInteractionListener());

		// monthDateArea.getDateArea().setToolTipProvider(myTTP);
		// dayDateArea.getDateArea().setToolTipProvider(myTTP);
		// topDayArea.getDateArea().setToolTipProvider(myTTP);

		ButtonGroup bg = new ButtonGroup();
		bg.add(weekButton);
		bg.add(dayButton);
		bg.add(monthButton);

		// So that we don't have PM and AM for USA
		ActivityInteractor.setDefaultDateTimeFormat(new SimpleDateFormat(
				"HH.mm"));

		// Specially looking buttons on Mac OS X
		if (MigUtil.isAqua("1.5")) { // If Leopard...
			((FlowLayout) northPanel.getLayout()).setHgap(0);
			weekButton.setForeground(Color.WHITE);
			todayButton.putClientProperty("JButton.buttonType",
					"segmentedTextured");
			todayButton.putClientProperty("JButton.segmentPosition", "only");
			separatedButton.putClientProperty("JButton.buttonType",
					"segmentedTextured");
			separatedButton
					.putClientProperty("JButton.segmentPosition", "only");
			dayButton.putClientProperty("JButton.buttonType",
					"segmentedTextured");
			dayButton.putClientProperty("JButton.segmentPosition", "first");
			weekButton.putClientProperty("JButton.buttonType",
					"segmentedTextured");
			weekButton.putClientProperty("JButton.segmentPosition", "middle");
			monthButton.putClientProperty("JButton.buttonType",
					"segmentedTextured");
			monthButton.putClientProperty("JButton.segmentPosition", "last");
		} else {
			todayButton.setOpaque(false);
			separatedButton.setOpaque(false);
			dayButton.setOpaque(false);
			weekButton.setOpaque(false);
			monthButton.setOpaque(false);
		}

	}

	private static final Category[] ZERO_CATS = new Category[0];

	/**
	 * Returns the categories for a point.
	 * 
	 * @param dateArea
	 *            The date area.
	 * @param p
	 *            The point relative to the upper left corner of the DateArea.
	 * @return The categories. May be empty but never null.
	 */
	@SuppressWarnings("unused")
	private Category[] getCategoriesForPoint(DefaultDateArea dateArea, Point p) {
		GridRow row = dateArea.getDateGrid().getRowAt(Grid.SECONDARY_DIMENSION,
				p, Integer.MAX_VALUE);
		if (row == null)
			return ZERO_CATS;

		CategoryFilter catFilter = (CategoryFilter) row.getFilter();
		if (catFilter == null)
			return ZERO_CATS;

		Category[] c2Arr = catFilter.getCategories();
		return c2Arr != null ? c2Arr : ZERO_CATS;
	}

	/**
	 * Returns if the activity has a date range that is considered a "whole day"
	 * activity.
	 * 
	 * @param subject
	 *            The activity.
	 * @return If the activity has a date range that is considered a "whole day"
	 *         activity.
	 */
	private boolean isWholeDay(Object subject) {
		return !((ActivityView) subject).getDateRangeForReading().getHasTime();
	}

	/**
	 * Returns if the activity is currently interacted with (e.g. drag).
	 * 
	 * @param subject
	 *            The activity.
	 * @return If the activity is currently interacted with (e.g. drag).
	 */
	private boolean isInteracting(Object subject) {
		StatesI states = ((ActivityView) subject).getModel().getStates();
		return states.areStatesSet(
				GenericStates.DRAGGING_BIT | GenericStates.RESIZE_END_BIT
						| GenericStates.RESIZE_START_BIT, false);
	}

	/**
	 * Returns if the activity is selected.
	 * 
	 * @param subject
	 *            The activity
	 * @return If the activity is selected.
	 */
	private boolean isSelected(Object subject) {
		Activity act = ((ActivityView) subject).getModel();
		if (act.getStates().isStateSet(GenericStates.SELECTED_BIT))
			return true;

		Object[] catIDs = act.getCategoryIDs();
		if (catIDs != null) {
			for (int i = 0; i < catIDs.length; i++) {
				Object sel = CategoryDepository.getCategory(catIDs[i])
						.getProperty(CategoryTreeBean.LABEL_SELECTED_KEY);
				if (sel instanceof Boolean && ((Boolean) sel).booleanValue())
					return true;
			}
		}
		return false;
	}

	/**
	 * Configure activities to take some color depending on the category
	 * (calendar) it is in.
	 */
	private void configureCategories() {
		int ev = CategoryStructureEvent.ADDED_CREATED;
		CategoryDepository.addBrokedCategory(new Category(calendarsID,
				"Calendars", null), AppFrame.class, ev);
		CategoryDepository.addBrokedCategory(new Category(homeID, "Home",
				calendarsID), AppFrame.class, ev);
		CategoryDepository.addBrokedCategory(new Category(workID, "Work",
				calendarsID), AppFrame.class, ev);
		CategoryDepository.addBrokedCategory(new Category(michaelID, "Michael",
				calendarsID), AppFrame.class, ev);
		CategoryDepository.addBrokedCategory(new Category(gregID, "Greg",
				calendarsID), AppFrame.class, ev);

		setCategoryOverride(homeID, new Color(236, 51, 34));
		setCategoryOverride(workID, new Color(0, 105, 220));
		setCategoryOverride(michaelID, new Color(248, 139, 0));
		setCategoryOverride(gregID, new Color(56, 172, 18));

		// Check the calendars to start with
		CategoryDepository.getCategory(calendarsID).setPropertyDeep(
				CategoryTreeBean.CHECK_SELECTED_KEY, Boolean.FALSE, true, null);
		CategoryDepository.getCategory(homeID).setPropertySilent(
				CategoryTreeBean.CHECK_SELECTED_KEY, Boolean.TRUE, null);
		CategoryDepository.getCategory(workID).setPropertySilent(
				CategoryTreeBean.CHECK_SELECTED_KEY, Boolean.TRUE, null);
	}

	private void setCategoryOverride(Object id, Color baseColor) {
		// Overrides for the colors in the category tree
		String bgCatTreeName = CategoryTreeBean.LEAF_CHECK_BACKGROUND_SHAPE_NAME;
		String outlineCatTreeName = CategoryTreeBean.LEAF_CHECK_OUTLINE_SHAPE_NAME;

		CategoryDepository.setOverride(id, bgCatTreeName, AShape.A_PAINT,
				baseColor);
		CategoryDepository.setOverride(id, outlineCatTreeName, AShape.A_PAINT,
				baseColor);

		// Overrides for the activities in the different date areas
		setActivityCategoryOverride(id, baseColor, "", 90);
		setActivityCategoryOverride(id, baseColor, "top_", 90);
		setActivityCategoryOverride(id, baseColor, "month_", 120);
	}

	private void setActivityCategoryOverride(Object id, Color baseColor,
			String namePrefix, int alpha) {
		String bgName = namePrefix + AShapeUtil.DEFAULT_BACKGROUND_SHAPE_NAME;
		String outlineName = namePrefix + AShapeUtil.DEFAULT_OUTLINE_SHAPE_NAME;
		String textName = namePrefix + AShapeUtil.DEFAULT_MAIN_TEXT_SHAPE_NAME;
		String titleName = namePrefix
				+ AShapeUtil.DEFAULT_TITLE_TEXT_SHAPE_NAME;

		CategoryDepository.setOverride(id, bgName, AShape.A_PAINT,
				GfxUtil.alphaColor(baseColor, alpha));
		CategoryDepository.setOverride(id, outlineName, AShape.A_PAINT,
				baseColor);
		CategoryDepository.setOverride(id, textName, AShape.A_PAINT, baseColor);
		CategoryDepository
				.setOverride(id, titleName, AShape.A_PAINT, baseColor);
	}

	private void configureActivities() {

		// Make selected look different
		configureSelectedActivityChanges("");
		configureSelectedActivityChanges("top_");

		// Make adjustments that is only for month activites. Will override the
		// CategoryDepository.setOverride()..

		// No outline
		ActivityInteractor.setStaticOverride("month_"
				+ AShapeUtil.DEFAULT_OUTLINE_SHAPE_NAME, AShape.A_PAINT,
				new OverrideFilter() {
					public Object getOverride(Object subject,
							Object defaultObject) {
						return null;
					}
				});

		// Background color depending on a few things
		ActivityInteractor.setStaticOverride("month_"
				+ AShapeUtil.DEFAULT_BACKGROUND_SHAPE_NAME, AShape.A_PAINT,
				new OverrideFilter() {
					public Object getOverride(Object subject,
							Object defaultObject) {
						if (isSelected(subject))
							defaultObject = GfxUtil.alphaColor(
									(Color) defaultObject, 235);

						return isWholeDay(subject) || isInteracting(subject)
								|| isSelected(subject) ? defaultObject : null;
					}
				});

		// No rounded corner for moving short activity
		ActivityInteractor.setStaticOverride("month_"
				+ AShapeUtil.DEFAULT_BACKGROUND_SHAPE_NAME, AShape.A_SHAPE,
				new OverrideFilter() {
					public Object getOverride(Object subject,
							Object defaultObject) {
						return isSelected(subject)
								&& isWholeDay(subject) == false ? new Rectangle(
								0, 0, 1, 1) : defaultObject;
					}
				});

		// White text for all but short activities.
		ActivityInteractor.setStaticOverride("month_"
				+ AShapeUtil.DEFAULT_TITLE_TEXT_SHAPE_NAME, AShape.A_PAINT,
				new OverrideFilter() {
					public Object getOverride(Object subject,
							Object defaultObject) {
						return isWholeDay(subject) || isInteracting(subject)
								|| isSelected(subject) ? Color.WHITE
								: defaultObject;
					}
				});

		// Add start time for short activities
		ActivityInteractor.setStaticOverride("month_"
				+ AShapeUtil.DEFAULT_TITLE_TEXT_SHAPE_NAME, AShape.A_TEXT,
				new OverrideFilter() {
					public Object getOverride(Object subject,
							Object defaultObject) {
						return isWholeDay(subject) ? defaultObject
								: "$startTime$ $summary$";
					}
				});
	}

	private void configureSelectedActivityChanges(String prefix) {
		// Opaque gradient background for selected
		ActivityInteractor.setStaticOverride(prefix
				+ AShapeUtil.DEFAULT_BACKGROUND_SHAPE_NAME, AShape.A_PAINT,
				new OverrideFilter() {
					public Object getOverride(Object subject,
							Object defaultObject) {
						if (isSelected(subject)) {
							ActivityView view = (ActivityView) subject;
							Color baseColor = GfxUtil.alphaColor(
									(Color) defaultObject, 255);
							Color c1 = GfxUtil.getCrossColor(baseColor,
									Color.WHITE, 0.15f);
							Color c2 = GfxUtil.getCrossColor(baseColor,
									Color.WHITE, 0.4f);
							Rectangle rect = view.getBounds()[0];
							return new GradientPaint(rect.x, 0, c1, rect.x
									+ rect.width, 0, c2);
						}
						return defaultObject;
					}
				});

		// White title for selected
		ActivityInteractor.setStaticOverride(prefix
				+ AShapeUtil.DEFAULT_TITLE_TEXT_SHAPE_NAME, AShape.A_PAINT,
				new OverrideFilter() {
					public Object getOverride(Object subject,
							Object defaultObject) {
						return isSelected(subject) ? Color.WHITE
								: defaultObject;
					}
				});

		// White text for selected
		ActivityInteractor.setStaticOverride(prefix
				+ AShapeUtil.DEFAULT_MAIN_TEXT_SHAPE_NAME, AShape.A_PAINT,
				new OverrideFilter() {
					public Object getOverride(Object subject,
							Object defaultObject) {
						return isSelected(subject) ? Color.WHITE
								: defaultObject;
					}
				});

		// Shadow visible for selected
		ActivityInteractor.setStaticOverride(prefix
				+ AShapeUtil.DEFAULT_SHADOW_SHAPE_NAME, AShape.A_VISIBILITY,
				new OverrideFilter() {
					public Object getOverride(Object subject,
							Object defaultObject) {
						return isSelected(subject) ? Visibility.TRUE
								: Visibility.FALSE;
					}
				});

		// Recurrency sub shape visible for recurrent activities
		ActivityInteractor.setStaticOverride(prefix + "recur_circle",
				AShape.A_VISIBILITY, new OverrideFilter() {
					public Object getOverride(Object subject,
							Object defaultObject) {
						return ((ActivityView) subject).getModel()
								.isRecurrent() ? Visibility.TRUE
								: Visibility.FALSE;
					}
				});

		// Bottom end time visible for dragging
		ActivityInteractor.setStaticOverride(prefix + "end_time",
				AShape.A_VISIBILITY, new OverrideFilter() {
					public Object getOverride(Object subject,
							Object defaultObject) {
						return isInteracting(subject) ? Visibility.TRUE
								: Visibility.FALSE;
					}
				});
	}

	/**
	 * Create a shape for end time when moving
	 * 
	 * @return The shape. Never <code>null</code>.
	 */
	private static AShape createEndTimeShape() {
		PlaceRect r = new AbsRect(AtStart.START0, AtStart.START0, AtEnd.END1,
				AtEnd.END1);
		Font f = new Font("sansserif", Font.PLAIN, 9);
		TextAShape endTime = new TextAShape("end_time", "$endTimeExcl$", r,
				TextAShape.TYPE_SINGLE_LINE, f, Color.WHITE, new AtEnd(-2),
				new AtEnd(0), GfxUtil.AA_HINT_LCD_HRGB);
		return endTime;
	}

	/**
	 * Create the recurrence shape
	 * 
	 * @return The shape. Not <code>null</code>.
	 */
	private static AShape createRecurrenceShape() {
		// Reuse names from the category bean so we only have to set one
		// override.

		PlaceRect r = new AbsRect(new AtEnd(-13), new AtStart(2),
				new AtEnd(-4), new AtStart(11));
		DrawAShape circle = new DrawAShape("recur_circle", new Ellipse2D.Float(
				0, 0, 1, 1), r, Color.WHITE, new BasicStroke(1.0f),
				GfxUtil.AA_HINT_ON);

		Polygon polyUp = new Polygon();
		polyUp.addPoint(0, 0);
		polyUp.addPoint(100, -141);
		polyUp.addPoint(200, 0);
		r = new AbsRect(new AtFraction(-0.2f), new AtFraction(0.2f),
				new AtFraction(0.45f), new AtFraction(0.7f));
		FillAShape arrowUp = new FillAShape("recur_arr_up", polyUp, r,
				Color.WHITE, GfxUtil.AA_HINT_ON);

		Polygon polyDown = new Polygon();
		polyDown.addPoint(0, -141);
		polyDown.addPoint(100, 0);
		polyDown.addPoint(200, -141);
		r = new AbsRect(new AtFraction(0.55f), new AtFraction(0.3f),
				new AtFraction(1.2f), new AtFraction(0.8f));
		FillAShape arrowDown = new FillAShape("recur_arr_down", polyDown, r,
				Color.WHITE, GfxUtil.AA_HINT_ON);

		circle.addSubShape(arrowUp);
		circle.addSubShape(arrowDown);
		return circle;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ArrayList getSelectedCategoryIDs() {
		ArrayList catIDs = new ArrayList();
		for (int i = catTree.getTree().getRowCount() - 1; i >= 0; i--) {
			Category cat = (Category) catTree.getTree().getPathForRow(i)
					.getLastPathComponent();
			Boolean sel = (Boolean) cat
					.getProperty(CategoryTreeBean.CHECK_SELECTED_KEY);
			if (sel != null && sel.booleanValue())
				catIDs.add(cat.getId());
		}
		return catIDs;
	}

	/**
	 * Set separate columns for categories (calendars) when bottom button is
	 * clicked.
	 */
	private void setSeparateCheckEnabledState() {
		separatedButton.setEnabled(currentDateArea == dayDateArea
				&& getSelectedCategoryIDs().size() > 1);
	}

	private void setHeaderCategoryIDs() {
		Object[] ids = separatedButton.isSelected()
				&& separatedButton.isEnabled() ? getSelectedCategoryIDs()
				.toArray() : null;
		dayDateArea.setCategoryRootIDs(ids);
		topDayArea.setCategoryRootIDs(ids);
	}

	private void setMode(int rangeType) {
		currentDateArea = dayDateArea;
		CardLayout cl = (CardLayout) mainParentPanel.getLayout();
		cl.show(mainParentPanel, "day");

		overviewDateArea.setSelectionBoundaryType(rangeType);

		MutableDateRange curRange = overviewDateArea.getDateArea()
				.getSelectedRange().getDateRangeClone();
		curRange.setSize(rangeType, 1, MutableDateRange.ALIGN_CENTER_DOWN);

		overviewDateArea.getDateArea().setSelectedRange(curRange);

		if (MigUtil.isAqua("1.5")) {
			weekButton.setForeground(weekButton.isSelected() ? Color.WHITE
					: Color.BLACK);
			dayButton.setForeground(dayButton.isSelected() ? Color.WHITE
					: Color.BLACK);
			monthButton.setForeground(monthButton.isSelected() ? Color.WHITE
					: Color.BLACK);
		}

		setSeparateCheckEnabledState();
	}

	// ***********************************
	// Code below is created by NetBeans.
	// ***********************************

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		showDayPanel = new JPanel(new FlowLayout());
		currentDay = new JLabel();
		overviewWestHeader = new com.miginfocom.beans.DateHeaderBean();
		overviewNorthHeader = new com.miginfocom.beans.DateHeaderBean();
		overviewVerticalLayout = new com.miginfocom.beans.GridDimensionLayoutBean();
		activityDayAShape = new com.miginfocom.beans.ActivityAShapeBean();
		activityMonthAShape = new com.miginfocom.beans.ActivityAShapeBean();
		weekLayout = new com.miginfocom.beans.GridDimensionLayoutBean();
		weekDateHeader = new com.miginfocom.beans.DateHeaderBean();
		dayLayout = new com.miginfocom.beans.GridDimensionLayoutBean();
		dayDateHeader = new com.miginfocom.beans.DateHeaderBean();
		yearConnector = new com.miginfocom.beans.DateGroupConnectorBean();
		dayTimeHeader = new com.miginfocom.beans.DateHeaderBean();
		dayCategoryHeader = new com.miginfocom.beans.NorthCategoryHeaderBean();
		topDayAShape = new com.miginfocom.beans.ActivityAShapeBean();
		westPanel = new javax.swing.JPanel();
		spacer2 = new javax.swing.JPanel();
		catTree = new com.miginfocom.beans.CategoryTreeBean();
		spacer1 = new javax.swing.JPanel();
		paintPanelBean2 = new com.miginfocom.beans.PaintPanelBean();
		dateSpinnerBean1 = new com.miginfocom.beans.DateSpinnerBean();
		overviewDateArea = new com.miginfocom.beans.DateAreaBean();
		southPanel = new javax.swing.JPanel();
		mainParentPanel = new javax.swing.JPanel();
		dayPanel = new javax.swing.JPanel();
		topDayPanel = new javax.swing.JPanel();
		topDayLeftPanel = new javax.swing.JPanel();
		yearLabel = new javax.swing.JLabel();
		allDayLabel = new javax.swing.JLabel();
		topDayArea = new com.miginfocom.beans.DateAreaBean();
		topEndSpacer = new javax.swing.JPanel();
		topDivider = new com.miginfocom.beans.PaintPanelBean();
		dayDateArea = new com.miginfocom.beans.DateAreaBean();
		// monthDateArea = new com.miginfocom.beans.DateAreaBean();
		northPanel = new com.miginfocom.beans.PaintPanelBean();
		spacer4 = new javax.swing.JPanel();
		todayButton = new javax.swing.JButton();
		spacer3 = new javax.swing.JPanel();
		dayButton = new javax.swing.JToggleButton();
		weekButton = new javax.swing.JToggleButton();
		monthButton = new javax.swing.JToggleButton();
		spacer5 = new javax.swing.JPanel();
		separatedButton = new javax.swing.JToggleButton();

		overviewWestHeader
				.setHeaderRows(new com.miginfocom.calendar.header.CellDecorationRow[] { new com.miginfocom.calendar.header.CellDecorationRow(
						com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_MONTH,
						new com.miginfocom.util.dates.DateFormatList("MMM",
								null),
						new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
								17.0f),
						new com.miginfocom.util.gfx.geometry.AbsRect(
								new com.miginfocom.util.gfx.geometry.numbers.AtStart(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtStart(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
										0.0f), null, null, null),
						(java.awt.Paint[]) new java.awt.Paint[] { new java.awt.Color(
								179, 181, 191) },
						new java.awt.Paint[] { new java.awt.Color(255, 255, 255) },
						new com.miginfocom.util.repetition.DefaultRepetition(0,
								1, null, null),
						new java.awt.Font[] { new java.awt.Font("Dialog", 1, 11) },
						new java.lang.Integer[] { null },
						new com.miginfocom.util.gfx.geometry.numbers.AtFraction(
								0.5f),
						new com.miginfocom.util.gfx.geometry.numbers.AtFraction(
								0.8f)) });
		overviewWestHeader
				.setBackgroundPaint(new java.awt.Color(160, 160, 160));
		overviewWestHeader
				.setGridLineExceptions(new com.miginfocom.calendar.grid.GridLineException[] { new com.miginfocom.calendar.grid.OffsetException(
						new com.miginfocom.calendar.grid.GridLineRepetition(0,
								2147483647, null, null, 0, null, null, null)) });
		overviewWestHeader
				.setLabelRotation(com.miginfocom.ashape.shapes.TextAShape.TYPE_SINGLE_LINE_ROT_CCW);
		overviewWestHeader
				.setTextAntiAlias(com.miginfocom.util.gfx.GfxUtil.AA_HINT_LCD_HRGB);

		overviewNorthHeader
				.setHeaderRows(new com.miginfocom.calendar.header.CellDecorationRow[] { new com.miginfocom.calendar.header.CellDecorationRow(
						com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_DAY,
						new com.miginfocom.util.dates.DateFormatList("2E", null),
						new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
								25.0f),
						new com.miginfocom.util.gfx.geometry.AbsRect(
								new com.miginfocom.util.gfx.geometry.numbers.AtStart(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtStart(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
										0.0f), null, null, null),
						(java.awt.Paint[]) null,
						new java.awt.Paint[] { new java.awt.Color(0, 0, 0) },
						new com.miginfocom.util.repetition.DefaultRepetition(0,
								1, null, null),
						new java.awt.Font[] { new java.awt.Font("Dialog", 0, 9) },
						new java.lang.Integer[] { null },
						new com.miginfocom.util.gfx.geometry.numbers.AtFraction(
								0.5f),
						new com.miginfocom.util.gfx.geometry.numbers.AtFraction(
								0.90000004f)) });
		overviewNorthHeader
				.setBackgroundPaint(new java.awt.Color(229, 233, 236));
		overviewNorthHeader
				.setExpandToCorner(com.miginfocom.calendar.datearea.DateAreaContainer.CORNER_EXPAND_BOTH);
		overviewNorthHeader
				.setTextAntiAlias(com.miginfocom.util.gfx.GfxUtil.AA_HINT_LCD_HRGB);

		overviewVerticalLayout
				.setRowSizeNormal(new com.miginfocom.util.gfx.geometry.SizeSpec(
						new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
								23.0f), null, null));

		activityDayAShape.setBackground(new java.awt.Color(235, 236, 240));
		activityDayAShape.setCornerRadius(10.0);
		activityDayAShape.setOutlinePaint(new java.awt.Color(191, 197, 204));
		activityDayAShape.setPaintContext("day");
		activityDayAShape.setRepaintPadding(new java.awt.Insets(4, 4, 8, 8));
		activityDayAShape.setShadowCornerRadius(16.0);
		activityDayAShape.setShadowPaint(new java.awt.Color(0, 0, 0, 213));
		activityDayAShape
				.setShadowPlaceRect(new com.miginfocom.util.gfx.geometry.AbsRect(
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						null, null, new java.awt.Insets(0, 1, 2, 1)));
		activityDayAShape.setShadowSliceSize(20);
		activityDayAShape.setTextFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
		activityDayAShape
				.setTextPlaceRect(new com.miginfocom.util.gfx.geometry.AbsRect(
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								3.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								13.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						null, null, null));
		activityDayAShape.setTitleFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
		activityDayAShape
				.setTitlePlaceRect(new com.miginfocom.util.gfx.geometry.AbsRect(
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								3.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								4.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
								-14.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								15.0f), null, null, null));
		activityDayAShape.setTitleTemplate("$startTime$");

		activityMonthAShape
				.setBackgroundPlaceRect(new com.miginfocom.util.gfx.geometry.AbsRect(
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						null, null, null));
		activityMonthAShape.setCornerRadius(16.0);
		activityMonthAShape.setOutlinePaint(new java.awt.Color(191, 194, 204));
		activityMonthAShape.setOutlineStrokeWidth(0.0F);
		activityMonthAShape.setPaintContext("week");
		activityMonthAShape
				.setResizeHandles(javax.swing.SwingConstants.HORIZONTAL);
		activityMonthAShape.setShapeNamePrefix("month_");
		activityMonthAShape.setTextFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
		activityMonthAShape.setTextTemplate("");
		activityMonthAShape
				.setTitleAlignX(new com.miginfocom.util.gfx.geometry.numbers.AtStart(
						5.0f));
		activityMonthAShape
				.setTitleAlignY(new com.miginfocom.util.gfx.geometry.numbers.AtStart(
						0.0f));
		activityMonthAShape.setTitleFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
		activityMonthAShape.setTitleForeground(new java.awt.Color(80, 80, 120));
		activityMonthAShape
				.setTitlePlaceRect(new com.miginfocom.util.gfx.geometry.AbsRect(
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						null, null, null));
		activityMonthAShape.setTitleTemplate("$summary$");

		activityMonthAShape
				.addMouseInteractionListener(new com.miginfocom.ashape.interaction.MouseInteractionListener() {
					public void mouseInteracted(
							com.miginfocom.ashape.interaction.MouseInteractionEvent evt) {
						activityMonthAShapeMouseInteracted(evt);
					}
				});

		weekLayout
				.setRowSizeNormal(new com.miginfocom.util.gfx.geometry.SizeSpec(
						new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
								80.0f), null, null));

		weekDateHeader
				.setHeaderRows(new com.miginfocom.calendar.header.CellDecorationRow[] {
						new com.miginfocom.calendar.header.CellDecorationRow(
								com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_CUSTOM,
								new com.miginfocom.util.dates.DateFormatList(
										"MMMM yyyy", null),
								new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
										25.0f),
								new com.miginfocom.util.gfx.geometry.AbsRect(
										new com.miginfocom.util.gfx.geometry.numbers.AtStart(
												0.0f),
										new com.miginfocom.util.gfx.geometry.numbers.AtStart(
												0.0f),
										new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
												0.0f),
										new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
												0.0f), null, null, null),
								(java.awt.Paint[]) null,
								new java.awt.Paint[] { new java.awt.Color(80,
										80, 80) },
								null,
								new java.awt.Font[] { new java.awt.Font(
										"SansSerif", 1, 16) },
								new java.lang.Integer[] { null },
								new com.miginfocom.util.gfx.geometry.numbers.AtFraction(
										0.5f),
								new com.miginfocom.util.gfx.geometry.numbers.AtFraction(
										0.5f)),
						new com.miginfocom.calendar.header.CellDecorationRow(
								com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_DAY,
								new com.miginfocom.util.dates.DateFormatList(
										"EEEE", null),
								new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
										21.0f),
								new com.miginfocom.util.gfx.geometry.AbsRect(
										new com.miginfocom.util.gfx.geometry.numbers.AtStart(
												0.0f),
										new com.miginfocom.util.gfx.geometry.numbers.AtStart(
												0.0f),
										new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
												0.0f),
										new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
												0.0f), null, null, null),
								(java.awt.Paint[]) null,
								new java.awt.Paint[] { new java.awt.Color(118,
										118, 118) },
								new com.miginfocom.util.repetition.DefaultRepetition(
										0, 1, null, null),
								new java.awt.Font[] { new java.awt.Font(
										"Dialog", 0, 10) },
								new java.lang.Integer[] { null },
								new com.miginfocom.util.gfx.geometry.numbers.AtFraction(
										0.5f),
								new com.miginfocom.util.gfx.geometry.numbers.AtFraction(
										0.5f)) });
		weekDateHeader.setBackgroundPaint(new java.awt.Color(255, 255, 255));
		weekDateHeader
				.setTextAntiAlias(com.miginfocom.util.gfx.GfxUtil.AA_HINT_LCD_HRGB);

		dayLayout
				.setCompressRowsFormat(com.miginfocom.beans.GridDimensionLayoutBean.TIME_OF_DAY);
		dayLayout
				.setRowSizeNormal(new com.miginfocom.util.gfx.geometry.SizeSpec(
						new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
								20.0f), null, null));

		dayDateHeader
				.setHeaderRows(new com.miginfocom.calendar.header.CellDecorationRow[] { new com.miginfocom.calendar.header.CellDecorationRow(
						com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_DAY,
						new com.miginfocom.util.dates.DateFormatList(
								"EEE, MMM d|EEE|1E", null),
						new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
								25.0f),
						new com.miginfocom.util.gfx.geometry.AbsRect(
								new com.miginfocom.util.gfx.geometry.numbers.AtStart(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtStart(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
										0.0f), null, null, null),
						(java.awt.Paint[]) null,
						new java.awt.Paint[] { new java.awt.Color(0, 0, 0) },
						new com.miginfocom.util.repetition.DefaultRepetition(0,
								1, null, null),
						new java.awt.Font[] { new java.awt.Font("Dialog", 0, 10) },
						new java.lang.Integer[] { null },
						new com.miginfocom.util.gfx.geometry.numbers.AtFraction(
								0.5f),
						new com.miginfocom.util.gfx.geometry.numbers.AtFraction(
								0.5f)) });
		dayDateHeader.setBackgroundPaint(new java.awt.Color(255, 255, 255));
		dayDateHeader
				.setTextAntiAlias(com.miginfocom.util.gfx.GfxUtil.AA_HINT_LCD_HRGB);

		yearConnector
				.setBoundaryType(com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_YEAR_MONTHS);
		yearConnector.setConnectedDateArea(overviewDateArea);
		yearConnector.setExpandCount(1);

		dayTimeHeader
				.setHeaderRows(new com.miginfocom.calendar.header.CellDecorationRow[] { new com.miginfocom.calendar.header.CellDecorationRow(
						com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_HOUR,
						new com.miginfocom.util.dates.DateFormatList("H.mm",
								null),
						new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
								46.0f),
						new com.miginfocom.util.gfx.geometry.AbsRect(
								new com.miginfocom.util.gfx.geometry.numbers.AtStart(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtStart(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
										0.0f), null, null, null),
						(java.awt.Paint[]) null,
						new java.awt.Paint[] { new java.awt.Color(161, 161, 161) },
						new com.miginfocom.util.repetition.DefaultRepetition(
								0,
								1,
								new com.miginfocom.util.gfx.geometry.numbers.AtStart(
										2.0f), null),
						new java.awt.Font[] { new java.awt.Font("Dialog", 0, 9) },
						new java.lang.Integer[] { null },
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
								-3.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								-7.0f)) });
		dayTimeHeader.setBackgroundPaint(new java.awt.Color(255, 255, 255));

		dayCategoryHeader
				.setHeaderLevels(new com.miginfocom.calendar.header.DefaultSubRowLevel[] { new com.miginfocom.calendar.header.DefaultSubRowLevel(
						"$gridRowName$",
						new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
								15.0f),
						new com.miginfocom.util.gfx.geometry.AbsRect(
								new com.miginfocom.util.gfx.geometry.numbers.AtStart(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtStart(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
										0.0f),
								new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
										0.0f), null, null, null),
						(java.awt.Paint[]) null,
						new java.awt.Paint[] { new java.awt.Color(102, 102, 102) },
						null,
						999,
						new java.awt.Font[] { new java.awt.Font("Dialog", 0, 9) },
						new java.lang.Integer[] { null },
						new com.miginfocom.util.gfx.geometry.numbers.AtFraction(
								0.5f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								2.0f), 1, 0) });
		dayCategoryHeader.setBackgroundPaint(new java.awt.Color(255, 255, 255));
		dayCategoryHeader.setCellBorder(javax.swing.BorderFactory
				.createMatteBorder(0, 1, 1, 0,
						new java.awt.Color(204, 204, 204)));

		topDayAShape.setOutlinePaint(null);
		topDayAShape.setPaintContext("top");
		topDayAShape.setResizeHandles(javax.swing.SwingConstants.HORIZONTAL);
		topDayAShape.setShadowCornerRadius(12.0);
		topDayAShape.setShadowPaint(new java.awt.Color(0, 0, 0, 213));
		topDayAShape
				.setShadowPlaceRect(new com.miginfocom.util.gfx.geometry.AbsRect(
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						null, null, new java.awt.Insets(0, 1, 2, 1)));
		topDayAShape.setShapeNamePrefix("top_");
		topDayAShape
				.setTitleAlignX(new com.miginfocom.util.gfx.geometry.numbers.AtStart(
						2.0f));
		topDayAShape
				.setTitleAlignY(new com.miginfocom.util.gfx.geometry.numbers.AtStart(
						1.0f));
		topDayAShape.setTitleFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
		topDayAShape.setTitleTemplate("$summary$");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(java.awt.Color.white);

		westPanel.setBackground(new java.awt.Color(229, 233, 236));
		westPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0,
				0, 1, new java.awt.Color(170, 170, 170)));
		westPanel.setMinimumSize(new java.awt.Dimension(190, 26));
		westPanel.setPreferredSize(new java.awt.Dimension(345, 100));
		westPanel.setLayout(new javax.swing.BoxLayout(westPanel,
				javax.swing.BoxLayout.Y_AXIS));

		spacer2.setMinimumSize(new java.awt.Dimension(10, 8));
		spacer2.setOpaque(false);
		spacer2.setPreferredSize(new java.awt.Dimension(10, 8));
		westPanel.add(spacer2);

		catTree.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 12,
				10, 10));
		catTree.setShowsRootHandles(true);
		catTree.setBackgroundPaint(new java.awt.Color(255, 255, 255));
		catTree.setFolderCheckVisible(false);
		catTree.setFolderCheckWidth(0);
		catTree.setFolderFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
		catTree.setFolderForeground(new java.awt.Color(80, 80, 120));
		catTree.setFolderIconTextGap(6);
		catTree.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
		catTree.setLeafCheckCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		catTree.setLeafCheckPlaceRect(new com.miginfocom.util.gfx.geometry.AbsRect(
				new com.miginfocom.util.gfx.geometry.numbers.AtStart(1.0f),
				new com.miginfocom.util.gfx.geometry.numbers.AtStart(4.0f),
				new com.miginfocom.util.gfx.geometry.numbers.AtEnd(-1.0f),
				new com.miginfocom.util.gfx.geometry.numbers.AtEnd(-4.0f),
				null, null, null));
		catTree.setLeafCheckWidth(15);
		catTree.setLeafLabelCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		catTree.setLeafLabelSelectable(true);
		catTree.setLeafRowHeight(21);
		catTree.setLeafSelectedFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
		catTree.setMinimumSize(new java.awt.Dimension(54, 140));
		catTree.setOpaque(false);
		catTree.addInteractionListener(new com.miginfocom.ashape.interaction.InteractionListener() {
			public void interactionOccured(
					com.miginfocom.ashape.interaction.InteractionEvent evt) {
				catTreeInteractionOccured(evt);
			}
		});
		// westPanel.add(catTree);

		spacer1.setMinimumSize(new java.awt.Dimension(10, 12));
		spacer1.setOpaque(false);
		spacer1.setPreferredSize(new java.awt.Dimension(10, 12));
		westPanel.add(spacer1);

		paintPanelBean2.setBackgroundPaint(new java.awt.Color(244, 248, 255));
		paintPanelBean2.setBorder(javax.swing.BorderFactory.createMatteBorder(
				1, 0, 1, 0, new java.awt.Color(165, 165, 165)));
		paintPanelBean2.setLayout(new java.awt.FlowLayout(
				java.awt.FlowLayout.CENTER, 0, 0));

		dateSpinnerBean1
				.setAlignment(com.miginfocom.calendar.spinner.SlimDateSpinner.ALIGN_CENTER);
		dateSpinnerBean1.setArrowShadowColor(new java.awt.Color(204, 204, 204));
		dateSpinnerBean1.setArrowSize(10);
		dateSpinnerBean1.setCalendarField(java.util.Calendar.YEAR);
		dateSpinnerBean1.setDateFormatString("yyyy");
		dateSpinnerBean1.setDateGroupConnector(yearConnector);
		dateSpinnerBean1.setEditable(false);
		dateSpinnerBean1.setEditorBorder(javax.swing.BorderFactory
				.createEmptyBorder(0, 5, 0, 5));
		dateSpinnerBean1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
		paintPanelBean2.add(dateSpinnerBean1);

		overviewDateArea.setBackground(new java.awt.Color(235, 238, 240));
		overviewDateArea.setNorthDateHeader(overviewNorthHeader);
		overviewDateArea.setSecondaryDimensionLayout(overviewVerticalLayout);
		overviewDateArea.setWestDateHeader(overviewWestHeader);
		overviewDateArea.setActivitiesSupported(false);
		overviewDateArea.setDateAreaInnerBorder(javax.swing.BorderFactory
				.createMatteBorder(0, 0, 0, 1,
						new java.awt.Color(160, 160, 160)));
		overviewDateArea.setDateAreaOuterBorder(javax.swing.BorderFactory
				.createMatteBorder(1, 0, 0, 0,
						new java.awt.Color(182, 197, 212)));
		overviewDateArea.setDesignTimeHelp(false);
		overviewDateArea.setDividerPaint(new java.awt.Color(160, 160, 160));
		overviewDateArea.setHorizontalGridLinePaintEven(new java.awt.Color(182,
				197, 212));
		overviewDateArea.setHorizontalGridLinePaintOdd(new java.awt.Color(182,
				197, 212));
		overviewDateArea
				.setLabelAntiAlias(com.miginfocom.util.gfx.GfxUtil.AA_HINT_LCD_HRGB);
		overviewDateArea.setLabelBorder(javax.swing.BorderFactory
				.createMatteBorder(1, 1, 0, 0, new java.awt.Color(255, 255,
						255, 100)));
		overviewDateArea.setLabelDateFormat("d");
		overviewDateArea.setLabelFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
		overviewDateArea.setLabelForeground(new java.awt.Color(51, 51, 51));
		overviewDateArea
				.setLabelNowBackground(new java.awt.Color(109, 138, 183));
		overviewDateArea.setLabelNowBorder(javax.swing.BorderFactory
				.createCompoundBorder(new javax.swing.border.MatteBorder(2, 0,
						0, 0, new java.awt.Color(0, 0, 0, 150)),
						new javax.swing.border.MatteBorder(1, 1, 1, 1,
								new java.awt.Color(0, 0, 0, 50))));
		overviewDateArea.setLabelNowForeground(java.awt.Color.white);
		overviewDateArea.setLabelNowShadowForeground(java.awt.Color.black);
		overviewDateArea
				.setLabelPlaceRect(new com.miginfocom.util.gfx.geometry.AbsRect(
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						null, null, null));
		overviewDateArea.setLabelShadowForeground(java.awt.Color.white);
		overviewDateArea.setLayerForGridLines(85);
		overviewDateArea.setOpaque(false);
		overviewDateArea
				.setSelectionBoundaryType(com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_MONTH);
		overviewDateArea.setSelectionMousePressedPaint(new java.awt.Color(208,
				217, 227, 195));
		overviewDateArea.setSelectionPaint(new java.awt.Color(208, 217, 227,
				195));
		overviewDateArea
				.setSelectionType(com.miginfocom.calendar.datearea.DateArea.SELECTION_TYPE_NORMAL);
		overviewDateArea.setVerticalGridLinePaintEven(new java.awt.Color(182,
				197, 212));
		overviewDateArea.setVerticalGridLinePaintOdd(new java.awt.Color(182,
				197, 212));
		overviewDateArea.setVerticalGridLineShowLast(true);
		overviewDateArea
				.addDateChangeListener(new com.miginfocom.util.dates.DateChangeListener() {
					public void dateRangeChanged(
							com.miginfocom.util.dates.DateChangeEvent evt) {
						overviewDateAreaDateRangeChanged(evt);
					}
				});
		farsiCalendar = new FarsiCalendar();

		farsiCalendar.addDaySelectionActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				Date start = farsiCalendar.getDate();
				start.setHours(0);
				start.setMinutes(0);
				start.setSeconds(0);
				Date end = farsiCalendar.getDate();
				end.setHours(23);
				end.setMinutes(59);
				end.setSeconds(59);

				DateRange dr = new DateRange();
				dr.setStartMillis(start.getTime());
				dr.setEndMillis(end.getTime(), true);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				currentDay.setText(sdf.format(farsiCalendar
						.getJalaliSelectedDate()));

				currentDateArea.getDateArea().setVisibleDateRange(dr);

				topDayArea.getDateArea().setVisibleDateRange(dr);

				currentDateArea.revalidate();
				topDayArea.revalidate();
			}
		});

		westPanel.add(farsiCalendar);

		JScrollPane jswest = new JScrollPane(westPanel);
		getContentPane().add(jswest, java.awt.BorderLayout.WEST);

		southPanel.setBackground(new java.awt.Color(255, 255, 255));
		southPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT,
				0, 0));
		JScrollPane jssouth = new JScrollPane(southPanel);
		getContentPane().add(jssouth, java.awt.BorderLayout.SOUTH);

		mainParentPanel.setBackground(new java.awt.Color(255, 255, 255));
		mainParentPanel.setLayout(new java.awt.CardLayout());

		dayPanel.setLayout(new java.awt.BorderLayout());

		topDayPanel.setLayout(new java.awt.BorderLayout());

		topDayLeftPanel.setBackground(java.awt.Color.white);
		topDayLeftPanel.setPreferredSize(new java.awt.Dimension(46, 10));
		topDayLeftPanel.setLayout(new java.awt.FlowLayout(
				java.awt.FlowLayout.CENTER, 5, 7));

		yearLabel.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
		yearLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		yearLabel.setText(String.valueOf(new java.util.GregorianCalendar()
				.get(Calendar.YEAR)));
		topDayLeftPanel.add(yearLabel);

		allDayLabel.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
		allDayLabel.setForeground(new java.awt.Color(161, 161, 161));
		allDayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		allDayLabel.setText("all-day");
		// topDayLeftPanel.add(allDayLabel);

		// topDayPanel.add(topDayLeftPanel, java.awt.BorderLayout.WEST);

		topDayArea
				.setActivityLayouts(new com.miginfocom.calendar.layout.ActivityLayout[] { new com.miginfocom.calendar.layout.TimeBoundsLayout(
						new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
								1.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								1.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
								-1.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								1.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						2,
						new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
								16.0f),
						null,
						null,
						new String[] { "TimeBounds" },
						new com.miginfocom.util.dates.BoundaryRounder(
								com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_DAY,
								true, true, false, null, null, new Integer(0))) });
		topDayArea.setActivityPaintContext("top");
		topDayArea.setNorthDateHeader(dayDateHeader);
		topDayArea
				.setVisibleDateRangeString("20070916T000000000-20070920T235959999");
		topDayArea.setWrapBoundary(null);
		topDayArea.setCategoryShowRoot(true);
		topDayArea.setDesignTimeHelp(false);
		topDayArea.setHorizontalGridLinePaintEven(new java.awt.Color(204, 204,
				204));
		topDayArea.setHorizontalGridLinePaintOdd(new java.awt.Color(204, 204,
				204));
		topDayArea.setHorizontalGridLineShowFirst(true);
		topDayArea.setVerticalGridLinePaintEven(new java.awt.Color(204, 204,
				204));
		topDayArea
				.setVerticalGridLinePaintOdd(new java.awt.Color(204, 204, 204));
		topDayArea.setVerticalGridLineShowFirst(true);
		topDayArea.setVerticalGridLineShowLast(true);

		showDayPanel.add(currentDay);

		topDayPanel.add(showDayPanel, java.awt.BorderLayout.CENTER);

		topEndSpacer.setBackground(java.awt.Color.white);
		topEndSpacer.setPreferredSize(new java.awt.Dimension(13, 10));
		topDayPanel.add(topEndSpacer, java.awt.BorderLayout.EAST);

		topDivider.setBackgroundPaint(new java.awt.Color(242, 242, 242));
		topDivider.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0,
				1, 0, new java.awt.Color(200, 200, 200)));
		topDivider.setMinimumSize(new java.awt.Dimension(100, 4));
		topDivider.setPreferredSize(new java.awt.Dimension(100, 4));
		topDivider.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				50, 0));
		topDayPanel.add(topDivider, java.awt.BorderLayout.SOUTH);

		dayPanel.add(topDayPanel, java.awt.BorderLayout.NORTH);

		dayDateArea
				.setActivityLayouts(new com.miginfocom.calendar.layout.ActivityLayout[] { new com.miginfocom.calendar.layout.TimeBoundsLayout(
						new com.miginfocom.util.gfx.geometry.numbers.AtFraction(
								-0.5f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(0.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtStart(
								-1.0f),
						new com.miginfocom.util.gfx.geometry.numbers.AtEnd(1.0f),
						-1, null, null, null, new String[] { "TimeBounds" },
						null) });
		dayDateArea.setActivityPaintContext("day");
		dayDateArea.setPrimaryDimension(javax.swing.SwingConstants.VERTICAL);
		dayDateArea
				.setPrimaryDimensionCellType(com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_MINUTE);
		dayDateArea.setPrimaryDimensionCellTypeCount(6);
		dayDateArea.setPrimaryDimensionLayout(dayLayout);
		dayDateArea
				.setVisibleDateRangeString("20070916T000000000-20070920T235959999");
		dayDateArea.setWestDateHeader(dayTimeHeader);
		dayDateArea.setWrapBoundary(new Integer(
				com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_DAY));
		dayDateArea.setCategoryShowRoot(true);
		dayDateArea.setDesignTimeHelp(false);
		dayDateArea.setHorizontalGridLinePaintEven(new java.awt.Color(204, 204,
				204));
		dayDateArea.setHorizontalGridLinePaintOdd(new java.awt.Color(229, 229,
				229));
		dayDateArea.setHorizontalGridLineShowFirst(true);
		dayDateArea.setHorizontalGridLineShowLast(true);
		dayDateArea.setMouseOverActivitiesOntop(true);
		dayDateArea.setSelectedActivitiesOntop(true);
		dayDateArea
				.setSelectionBoundaryType(com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_MINUTE);
		dayDateArea
				.setSelectionType(com.miginfocom.calendar.datearea.DateArea.SELECTION_TYPE_NORMAL);
		dayDateArea.setShowNoFitIcon(true);
		dayDateArea.setVerticalGridLinePaintEven(new java.awt.Color(204, 204,
				204));
		dayDateArea.setVerticalGridLinePaintOdd(new java.awt.Color(204, 204,
				204));
		dayDateArea.setVerticalGridLineShowFirst(true);
		dayDateArea
				.addDateChangeListener(new com.miginfocom.util.dates.DateChangeListener() {
					public void dateRangeChanged(
							com.miginfocom.util.dates.DateChangeEvent evt) {
						dayDateAreaDateRangeChanged(evt);
					}
				});
		dayPanel.add(dayDateArea, java.awt.BorderLayout.CENTER);

		mainParentPanel.add(dayPanel, "day");

		// monthDateArea
		// .setActivityLayouts(new
		// com.miginfocom.calendar.layout.ActivityLayout[] {
		// new com.miginfocom.calendar.layout.TimeBoundsLayout(
		// new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
		// 1.0f),
		// new com.miginfocom.util.gfx.geometry.numbers.AtStart(
		// 0.0f),
		// new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
		// 0.0f),
		// new com.miginfocom.util.gfx.geometry.numbers.AtStart(
		// 14.0f),
		// new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
		// 0.0f),
		// 0,
		// new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
		// 12.0f),
		// new com.miginfocom.util.gfx.geometry.numbers.AtFixed(
		// 8.0f),
		// null,
		// new String[] { "TimeBounds" },
		// new com.miginfocom.util.dates.BoundaryRounder(
		// com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_DAY,
		// true, true, false, null, null,
		// new Integer(0))),
		// new com.miginfocom.calendar.layout.FlexGridLayout(
		// new com.miginfocom.util.gfx.geometry.numbers.AtStart(
		// 0.0f),
		// new com.miginfocom.util.gfx.geometry.numbers.AtStart(
		// 1.0f),
		// true,
		// true,
		// new Integer(1),
		// null,
		// new Integer(13),
		// 1,
		// 0,
		// new com.miginfocom.util.gfx.geometry.AbsRect(
		// new com.miginfocom.util.gfx.geometry.numbers.AtStart(
		// 0.0f),
		// new com.miginfocom.util.gfx.geometry.numbers.AtStart(
		// 14.0f),
		// new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
		// 0.0f),
		// new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
		// 0.0f), null, null, null),
		// new String[] { "FlexGrid" }) });
		// monthDateArea.setActivityPaintContext("week");
		// monthDateArea.setNorthDateHeader(weekDateHeader);
		// monthDateArea.setSecondaryDimensionLayout(weekLayout);
		// monthDateArea.setDesignTimeHelp(false);
		// monthDateArea.setDividerPaint(new java.awt.Color(161, 165, 180));
		// monthDateArea.setHorizontalGridLinePaintEven(new java.awt.Color(204,
		// 204, 204));
		// monthDateArea.setHorizontalGridLinePaintOdd(new java.awt.Color(204,
		// 204, 204));
		// monthDateArea.setHorizontalGridLineShowFirst(true);
		// monthDateArea
		// .setLabelAlignX(new
		// com.miginfocom.util.gfx.geometry.numbers.AtFraction(
		// 1.0f));
		// monthDateArea
		// .setLabelAlignY(new com.miginfocom.util.gfx.geometry.numbers.AtStart(
		// 0.0f));
		// monthDateArea
		// .setLabelAntiAlias(com.miginfocom.util.gfx.GfxUtil.AA_HINT_LCD_HRGB);
		// monthDateArea.setLabelDateFormat("d");
		// monthDateArea.setLabelFirstDateFormat("d");
		// monthDateArea.setLabelFont(new java.awt.Font("SansSerif", 0, 10)); //
		// NOI18N
		// monthDateArea.setLabelForeground(new java.awt.Color(48, 48, 48));
		// monthDateArea
		// .setLabelPlaceRect(new com.miginfocom.util.gfx.geometry.AbsRect(
		// new com.miginfocom.util.gfx.geometry.numbers.AtStart(
		// 0.0f),
		// new com.miginfocom.util.gfx.geometry.numbers.AtStart(
		// 2.0f),
		// new com.miginfocom.util.gfx.geometry.numbers.AtEnd(
		// -3.0f),
		// new com.miginfocom.util.gfx.geometry.numbers.AtStart(
		// 15.0f), null, null, null));
		// monthDateArea.setLayoutOptimizeBoundary(new Integer(
		// com.miginfocom.util.dates.DateRangeI.RANGE_TYPE_WEEK));
		// monthDateArea.setSelectionMousePressedPaint(new java.awt.Color(251,
		// 248, 244));
		// monthDateArea
		// .setSelectionType(com.miginfocom.calendar.datearea.DateArea.SELECTION_TYPE_NORMAL);
		// monthDateArea.setVerticalGridLinePaintEven(new java.awt.Color(204,
		// 204,
		// 204));
		// monthDateArea.setVerticalGridLinePaintOdd(new java.awt.Color(204,
		// 204,
		// 204));
		// monthDateArea
		// .addDateChangeListener(new
		// com.miginfocom.util.dates.DateChangeListener() {
		// public void dateRangeChanged(
		// com.miginfocom.util.dates.DateChangeEvent evt) {
		// monthDateAreaDateRangeChanged(evt);
		// }
		// });
		// mainParentPanel.add(monthDateArea, "month");
		JScrollPane js = new JScrollPane(mainParentPanel);
		getContentPane().add(js, java.awt.BorderLayout.CENTER);
		northPanel.setBackgroundPaint(new java.awt.Color(229, 233, 236));
		northPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0,
				1, 0, new java.awt.Color(100, 100, 100)));
		northPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				7, 7));

		spacer4.setOpaque(false);
		northPanel.add(spacer4);

		todayButton.setText(" امروز ");
		todayButton.setDefaultCapable(false);
		todayButton.setFocusPainted(false);
		todayButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				todayButtonActionPerformed(evt);
			}
		});
		northPanel.add(todayButton);

		spacer3.setOpaque(false);
		spacer3.setPreferredSize(new java.awt.Dimension(300, 12));
		northPanel.add(spacer3);

		dayButton.setText("   روز   ");
		dayButton.setFocusPainted(false);
		dayButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dayButtonActionPerformed(evt);
			}
		});
		// northPanel.add(dayButton);

		weekButton.setSelected(true);
		weekButton.setText("  هفته  ");
		weekButton.setFocusPainted(false);
		weekButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// weekButtonActionPerformed(evt);
			}
		});
		// northPanel.add(weekButton);

		monthButton.setText(" ماه ");
		monthButton.setFocusPainted(false);
		monthButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// monthButtonActionPerformed(evt);
			}
		});
		// northPanel.add(monthButton);

		spacer5.setOpaque(false);
		spacer5.setPreferredSize(new java.awt.Dimension(50, 12));
		northPanel.add(spacer5);

		separatedButton.setText(" Show Separated Calendars ");
		separatedButton.setEnabled(false);
		separatedButton.setFocusPainted(false);
		separatedButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				separatedButtonActionPerformed(evt);
			}
		});
		// northPanel.add(separatedButton);
		JScrollPane jsnorth = new JScrollPane(northPanel);
		getContentPane().add(jsnorth, java.awt.BorderLayout.PAGE_START);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	@SuppressWarnings("unused")
	private void weekButtonActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_weekButtonActionPerformed
	{// GEN-HEADEREND:event_weekButtonActionPerformed
		setMode(DateRangeI.RANGE_TYPE_WEEK);
	}// GEN-LAST:event_weekButtonActionPerformed

	@SuppressWarnings("unused")
	private void monthButtonActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_monthButtonActionPerformed
	{// GEN-HEADEREND:event_monthButtonActionPerformed
		setMode(DateRangeI.RANGE_TYPE_MONTH);
	}// GEN-LAST:event_monthButtonActionPerformed

	private void dayButtonActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_dayButtonActionPerformed
	{// GEN-HEADEREND:event_dayButtonActionPerformed
		setMode(DateRangeI.RANGE_TYPE_DAY);
	}// GEN-LAST:event_dayButtonActionPerformed

	@SuppressWarnings("unused")
	private void monthDateAreaDateRangeChanged(
			com.miginfocom.util.dates.DateChangeEvent evt)// GEN-FIRST:event_monthDateAreaDateRangeChanged
	{// GEN-HEADEREND:event_monthDateAreaDateRangeChanged
		// Code for click on the month date grid

		// if (evt.getType() == DateChangeEvent.SELECTED)
		// JOptionPane.showMessageDialog(this, "You pressed: " +
		// evt.getNewRange());
	}// GEN-LAST:event_monthDateAreaDateRangeChanged

	private void catTreeInteractionOccured(
			com.miginfocom.ashape.interaction.InteractionEvent evt) {// GEN-FIRST:event_calendarsTreeInteractionOccured
		if (CategoryTreeBean.CHECK_CLICKED.equals(evt.getCommand().getValue()))
			currentDateArea.getDateArea().recreateActivityViews();

		setSeparateCheckEnabledState();
		setHeaderCategoryIDs();
	}// GEN-LAST:event_calendarsTreeInteractionOccured

	private void activityMonthAShapeMouseInteracted(
			com.miginfocom.ashape.interaction.MouseInteractionEvent evt)// GEN-FIRST:event_activityMonthAShapeMouseInteracted
	{// GEN-HEADEREND:event_activityMonthAShapeMouseInteracted
		// Code to show a dialog when you click an activity in the month view

		// if (evt.getEventKey() == MouseKeyInteractor.MOUSE_CLICK) {
		// Component comp = evt.getOriginalEvent().getComponent();
		//
		// if (comp == currentDateArea.getDateArea()) {
		// ActivityView view = (ActivityView)
		// evt.getMouseKeyInteractor().getInteracted();
		// JOptionPane.showMessageDialog(comp, "You clicked: " +
		// view.getModel().getSummary());
		// }
		// }
	}// GEN-LAST:event_activityMonthAShapeMouseInteracted

	// You should make a DateChangeEvent
	private void overviewDateAreaDateRangeChanged(
			com.miginfocom.util.dates.DateChangeEvent evt) {// GEN-FIRST:event_overviewDateAreaDateRangeChanged
		if (evt.getType() == DateChangeEvent.SELECTED) {
			currentDateArea.getDateArea()
					.setVisibleDateRange(evt.getNewRange());
			topDayArea.getDateArea().setVisibleDateRange(evt.getNewRange());

			currentDateArea.revalidate();
			topDayArea.revalidate();
		}
	}// GEN-LAST:event_overviewDateAreaDateRangeChanged

	@SuppressWarnings("deprecation")
	private void todayButtonActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_todayButtonActionPerformed
	{// GEN-HEADEREND:event_todayButtonActionPerformed
		DateRange todayRange = new DateRange();
		Date s = new Date();
		s.setHours(0);
		s.setMinutes(0);
		s.setSeconds(0);
		Date e = new Date();
		e.setHours(23);
		e.setMinutes(59);
		e.setSeconds(59);
		todayRange.setStartMillis(s.getTime());
		todayRange.setEndMillis(e.getTime(), true);
		// int bType = overviewDateArea.getSelectionBoundaryType();
		// todayRange.setSize(bType, 1, MutableDateRange.ALIGN_CENTER_DOWN);
		farsiCalendar.setDate(new Date());
		overviewDateArea.getDateArea().setSelectedRange(todayRange);
	}// GEN-LAST:event_todayButtonActionPerformed

	private void separatedButtonActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_separatedButtonActionPerformed
	{// GEN-HEADEREND:event_separatedButtonActionPerformed
		if (separatedButton.isSelected() && separatedButton.isEnabled()) {
			separatedButton.setForeground(Color.WHITE);
			dayDateArea.setCategoryHeader(dayCategoryHeader);
		} else {
			separatedButton.setForeground(Color.BLACK);
			dayDateArea.setCategoryHeader(null);
		}

		setHeaderCategoryIDs();
		currentDateArea.revalidate();
	}// GEN-LAST:event_separatedButtonActionPerformed

	private void dayDateAreaDateRangeChanged(
			com.miginfocom.util.dates.DateChangeEvent evt)// GEN-FIRST:event_dayDateAreaDateRangeChanged
	{// GEN-HEADEREND:event_dayDateAreaDateRangeChanged
		// This is the code that creates an activity by dragging in the day/days
		// date area.
		// if (evt.getType() == DateChangeEvent.PRESSED) {
		//
		// if (newCreatedAct == null
		// && evt.getNewRange().getMillisSpanned(false, false) > 45 * 60 * 1000)
		// {
		// newCreatedAct = new DefaultActivity(evt.getNewRange(),
		// new Long(new Random().nextLong()));
		// newCreatedAct.setSummary("New Event");
		// //
		// newCreatedAct.addCategoryID(homeID, -1);
		// ActivityDepository.getInstance(
		// dayDateArea.getActivityDepositoryContext())
		// .addBrokedActivity(newCreatedAct, this,
		// TimeSpanListEvent.ADDED_CREATED);
		// } else {
		// try {
		// newCreatedAct.setBaseDateRange(evt.getNewRange());
		// } catch (Exception ex) {
		// }
		// }
		// } else if (evt.getType() == DateChangeEvent.SELECTED) {
		// if (newCreatedAct != null) {
		// newCreatedAct.getStates().setStates(GenericStates.SELECTED_BIT,
		// true);
		// newCreatedAct = null;
		// }
		// }
	}// GEN-LAST:event_dayDateAreaDateRangeChanged

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private com.miginfocom.beans.ActivityAShapeBean activityDayAShape;
	private com.miginfocom.beans.ActivityAShapeBean activityMonthAShape;
	private javax.swing.JLabel allDayLabel;
	private com.miginfocom.beans.CategoryTreeBean catTree;
	private com.miginfocom.beans.DateSpinnerBean dateSpinnerBean1;
	private javax.swing.JToggleButton dayButton;
	private com.miginfocom.beans.NorthCategoryHeaderBean dayCategoryHeader;
	private com.miginfocom.beans.DateAreaBean dayDateArea;
	private com.miginfocom.beans.DateHeaderBean dayDateHeader;
	private com.miginfocom.beans.GridDimensionLayoutBean dayLayout;
	private javax.swing.JPanel dayPanel;
	private com.miginfocom.beans.DateHeaderBean dayTimeHeader;
	private javax.swing.JPanel mainParentPanel;
	private javax.swing.JToggleButton monthButton;
	// private com.miginfocom.beans.DateAreaBean monthDateArea;
	private com.miginfocom.beans.PaintPanelBean northPanel;
	private com.miginfocom.beans.DateAreaBean overviewDateArea;
	private com.miginfocom.beans.DateHeaderBean overviewNorthHeader;
	private com.miginfocom.beans.GridDimensionLayoutBean overviewVerticalLayout;
	private com.miginfocom.beans.DateHeaderBean overviewWestHeader;
	private com.miginfocom.beans.PaintPanelBean paintPanelBean2;
	private javax.swing.JToggleButton separatedButton;
	private javax.swing.JPanel southPanel;
	private javax.swing.JPanel spacer1;
	private javax.swing.JPanel spacer2;
	private javax.swing.JPanel spacer3;
	private javax.swing.JPanel spacer4;
	private javax.swing.JPanel spacer5;
	private javax.swing.JButton todayButton;
	private com.miginfocom.beans.ActivityAShapeBean topDayAShape;
	private com.miginfocom.beans.DateAreaBean topDayArea;
	private javax.swing.JPanel topDayLeftPanel;
	private javax.swing.JPanel topDayPanel;
	private com.miginfocom.beans.PaintPanelBean topDivider;
	private javax.swing.JPanel topEndSpacer;
	private javax.swing.JToggleButton weekButton;
	private com.miginfocom.beans.DateHeaderBean weekDateHeader;
	private com.miginfocom.beans.GridDimensionLayoutBean weekLayout;
	private javax.swing.JPanel westPanel;
	private com.miginfocom.beans.DateGroupConnectorBean yearConnector;
	private javax.swing.JLabel yearLabel;
	private JPanel showDayPanel;
	private JLabel currentDay;

	private FarsiCalendar farsiCalendar;

	// End of variables declaration//GEN-END:variables

	@SuppressWarnings("serial")
	static class CatCheckedFilter implements Filter {
		public boolean accept(Object o) {
			if (o instanceof Categorizable) { // Activities are Categorizable...
				Object[] catIDs = ((Categorizable) o).getCategoryIDs();
				if (catIDs != null) {
					for (int c = 0; c < catIDs.length; c++) {
						Category cat = CategoryDepository
								.getCategory(catIDs[c]);
						if (cat != null
								&& MigUtil
										.isTrue(cat
												.getProperty(CategoryTreeBean.CHECK_SELECTED_KEY)) == false) {
							return false;
						}
					}
				}
			}
			return true;
		}

		public String getName() {
			return "CatCheckedFilter";
		}
	}

	/**
	 * Filters out or in whole day activities
	 */
	@SuppressWarnings("serial")
	static class DayActsFilter implements Filter {
		boolean showMultiDays;

		public DayActsFilter(boolean showMultiDays) {
			this.showMultiDays = showMultiDays;
		}

		public boolean accept(Object o) {
			return showMultiDays
					^ ((Activity) o).getDateRangeForReading().getHasTime();
		}

		public String getName() {
			return "DayActsFilter";
		}
	}

	/**
	 * Listens for interactions with the activities
	 */
	@SuppressWarnings("unused")
	private class ActivityInteractionListener implements InteractionListener {
		public void interactionOccured(InteractionEvent e) {
			final Object o = e.getInteractor().getInteracted();

			if (o instanceof ActivityView
					&& e.getSourceEvent() instanceof MouseEvent) {
				final ActivityView actView = (ActivityView) o;
				final DefaultDateArea dateArea = (DefaultDateArea) actView
						.getContainer();

				JPopupMenu pop = new JPopupMenu();
				final Point p = ((MouseEvent) e.getSourceEvent()).getPoint();
				Object commandValue = e.getCommand().getValue();

				if (DefaultDateArea.AE_POPUP_TRIGGER.equals(commandValue)) {
					boolean hasTime = actView.getModel().getBaseDateRange()
							.getHasTime();
					String menuText = hasTime ? "Make All Day Event"
							: "Make Regular Event";
					pop.add(menuText).addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Activity act = ((ActivityView) o).getModel();
							MutableDateRange dr = act.getDateRangeClone();
							dr.setHasTime(!dr.getHasTime());

							try {
								act.setBaseDateRange(dr.getImmutable());
							} catch (PropertyVetoException e1) {
							}

							// monthDateArea.getDateArea().recreateActivityViews();
							dayDateArea.getDateArea().recreateActivityViews();
							topDayArea.getDateArea().recreateActivityViews();
						}
					});

					pop.show(actView.getContainer(), p.x, p.y);

				} else if (DefaultDateArea.AE_DOUBLE_CLICKED
						.equals(commandValue)) {
					Activity act = ((ActivityView) o).getModel();
					String summary = JOptionPane.showInputDialog(dateArea,
							"Please enter a summary", act.getSummary());
					if (summary != null)
						act.setSummary(summary);
				}
			}
		}
	}

}
