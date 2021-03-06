package si.bleedy.btc;

import javax.swing.table.AbstractTableModel;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author bratwurzt
 */
public class BeeTeeMagnetsTableModel extends AbstractTableModel
{
  public static final int COL_HASH = 0;
  public static final int COL_TITLE = 1;
  public static final int COL_SEED = 2;
  public static final int COL_LEECH = 3;
  public static final int COL_LINK = 4;
  public static final int COL_SOURCE = 5;
  protected static final int COLUMN_COUNT = 6;
  protected NumberFormat m_numberFormat;

  protected List<MagnetURI> m_entityList;

  public BeeTeeMagnetsTableModel(Map<String, MagnetURI> map)
  {
    m_entityList = new ArrayList<MagnetURI>(map.values());
    m_numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
  }

  public int getRowCount()
  {
    return m_entityList.size();
  }

  public int getColumnCount()
  {
    return COLUMN_COUNT;
  }

  public String getColumnName(int columnIndex)
  {
    switch (columnIndex)
    {
      case COL_HASH:
        return "Hash";

      case COL_TITLE:
        return "Title";

      case COL_SEED:
        return "Seed";

      case COL_LEECH:
        return "Leech";

      case COL_LINK:
        return "Link";

      case COL_SOURCE:
        return "Source page";

      default:
        return "?";
    }
  }

  public Class<?> getColumnClass(int columnIndex)
  {
    switch (columnIndex)
    {
      case COL_HASH:
      case COL_TITLE:
      case COL_LINK:
      case COL_SOURCE:
        return String.class;

      case COL_SEED:
      case COL_LEECH:
        return Integer.class;

      default:
        return String.class;
    }
  }

  public Object getValueAt(int rowIndex, int columnIndex)
  {
    MagnetURI magnetLink = m_entityList.get(rowIndex);
    try
    {
      switch (columnIndex)
      {
        case COL_HASH:
          return magnetLink.getBtih();

        case COL_TITLE:
          return magnetLink.getDisplayName();

        case COL_SEED:
          return magnetLink.getSeeders();

        case COL_LEECH:
          return magnetLink.getLeechers();

        case COL_LINK:
            return magnetLink.getHref();

        case COL_SOURCE:
            return magnetLink.getSourcePage();

        default:
          return "damn you, sky!";
      }
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return "damn you, sky!";
  }

  public List<MagnetURI> getEntityList()
  {
    return m_entityList;
  }
}
