package Fancy;

import java.awt.Color;

public class Theme {
    public final String name;
    public final Color background;
    public final Color primary;
    public final Color primaryDark;
    public final Color board;
    public final Color tile;
    public final Color text;
    public final Color textDark;
    public final Color accent;

    public Theme(String name, Color background, Color primary, Color primaryDark, Color board, Color tile, Color text, Color textDark, Color accent) {
        this.name = name;
        this.background = background;
        this.primary = primary;
        this.primaryDark = primaryDark;
        this.board = board;
        this.tile = tile;
        this.text = text;
        this.textDark = textDark;
        this.accent = accent;
    }

    public static final Theme EARTH = new Theme(
            "Earth",
            new Color(0xB02222),
            new Color(0xE26A12),
            new Color(0xC85A0E),
            new Color(0xD8B59C),
            new Color(0x8B3B06),
            Color.WHITE,
            new Color(0x111111),
            new Color(0xFFD700)
    );

    public static final Theme FIRE = new Theme(
            "Fire",
            new Color(0x4A0E0E),
            new Color(0xFF5A1F),
            new Color(0xD9480F),
            new Color(0xF4C08A),
            new Color(0x9C2A00),
            Color.WHITE,
            new Color(0x24110D),
            new Color(0xFFD166)
    );

    public static final Theme WATER = new Theme(
            "Water",
            new Color(0x0B2D4F),
            new Color(0x1E88E5),
            new Color(0x1565C0),
            new Color(0xB3D9FF),
            new Color(0x0B5FA5),
            Color.WHITE,
            new Color(0x0E1C2B),
            new Color(0x64FFDA)
    );

    public static Theme[] presets() {
        return new Theme[]{EARTH, FIRE, WATER};
    }

    public static Theme byName(String name) {
        for (Theme theme : presets()) {
            if (theme.name.equalsIgnoreCase(name)) {
                return theme;
            }
        }
        return null;
    }
}