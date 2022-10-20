public int imgMinThum(int acWidth, int acHeight, out int height)
        {
            height = 207;
            int width = 207;
            if (acWidth < 207) { height = acHeight; return acWidth; }
            for (width = 207; width > 200; width--)
            {
                height = (width * acHeight) / acWidth;
                if (height <= 207) break;
            }
            return width;
        }
