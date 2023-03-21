package aruaru.common;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.Separators;

/**
 * JacksonのJson整形のときに、「 : 」 → 「: 」で区切るようにするため。
 */
public class JacksonPrettyPrinter extends DefaultPrettyPrinter {

  public JacksonPrettyPrinter() {
    _arrayIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
    // インデントのスペース幅と改行コードを指定
    // Text Blocksを使用したテストでのassertのためにひとまず\nを指定
    _objectIndenter = new DefaultIndenter("  ", "\n");
  }

  public JacksonPrettyPrinter(DefaultPrettyPrinter base) {
    super(base);
  }

  @Override
  public JacksonPrettyPrinter createInstance() {
    if (getClass() != JacksonPrettyPrinter.class) {
      throw new IllegalStateException("Failed `createInstance()`: " + getClass().getName()
          + " does not override method; it has to");
    }
    return new JacksonPrettyPrinter(this);
  }

  @Override
  public JacksonPrettyPrinter withSeparators(Separators separators) {
    this._separators = separators;
    this._objectFieldValueSeparatorWithSpaces = separators.getObjectFieldValueSeparator() + " ";
    return this;
  }

}
