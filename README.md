public byte[] Crop(string Img, int WidthTemp, int HeightTemp, int X, int Y, int ImgWidthTemp, int ImgHeightTemp)
        {
            try
            {
                using (SD.Image OriginalImage = SD.Image.FromFile(Img))
                {
                    using (SD.Bitmap bmp = new SD.Bitmap(WidthTemp, HeightTemp))
                    {
                        bmp.SetResolution(OriginalImage.HorizontalResolution, OriginalImage.VerticalResolution);
                        using (SD.Graphics Graphic = SD.Graphics.FromImage(bmp))
                        {
                            Graphic.SmoothingMode = SmoothingMode.AntiAlias;
                            Graphic.InterpolationMode = InterpolationMode.HighQualityBicubic;
                            Graphic.PixelOffsetMode = PixelOffsetMode.HighQuality;

                            Graphic.DrawImage(OriginalImage, new SD.Rectangle(0, 0, WidthTemp, HeightTemp), X, Y, ImgWidthTemp, ImgHeightTemp, SD.GraphicsUnit.Pixel);
                            //Graphic.DrawImage(OriginalImage, new SD.Rectangle(X, Y, ImgWidthTemp, ImgHeightTemp));
                            MemoryStream ms = new MemoryStream();
                            bmp.Save(ms, OriginalImage.RawFormat);
                            return ms.GetBuffer();
                        }
                    }
                }
            }
            catch (Exception Ex)
            {
                throw (Ex);
            }
        }
