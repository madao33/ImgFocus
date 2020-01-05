/**图像评价
 * @author madao
 * @time 2020年1月5日10:55:00
 * @ver 1.0
 */
package ImgValue;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

public class ImgEva {
	/**图像评价调用函数
	 * @param src-mat矩阵，输入原始图像
	 * @return value-doubel类型，图像评价指标，对焦越准确，值越大
	 */
	public static double ImgValue(Mat src, double thresh) {
		Mat gray = src.clone();
		Mat dstx = src.clone();
        Mat dsty = src.clone();
		 //灰度化处理图片
		Imgproc.cvtColor(src,gray,Imgproc.COLOR_RGB2GRAY);

		//X方向sobel处理
		Imgproc.Sobel(gray, dstx, -1, 1, 0, 3, 1, 0, Core.BORDER_DEFAULT);
//		HighGui.imshow("X方向", dstx);
//		HighGui.waitKey(0);
		//Y方向sobel处理
		Imgproc.Sobel(gray, dsty, -1, 0, 1, 3, 1, 0, Core.BORDER_DEFAULT);
//		HighGui.imshow("Y方向", dsty);
//		HighGui.waitKey(0);
		
		Core.addWeighted(dstx, 0.5, dsty, 0.5, 0, gray);
//		HighGui.imshow("sobel算子处理后图像", gray);
//		HighGui.waitKey(0);
		double value = mean(dstx,dsty, thresh);
//		System.out.print(value + "\n");
		return value;
	}
	
	/**sobel算子对焦评价函数
	 * @author: madao
 	 * @param:dstx-x方向sobel处理后图像，dsty-y方向sobel处理后图像；thresh-调焦评价阈值，影响对焦曲线
	 * @return：
	 */
	private static double mean(Mat dstx, Mat dsty, double thesh) {
		// TODO Auto-generated method stub
		int datax = 0, datay = 0;
		byte[] data = new byte[dstx.channels()];
		double value = 0;
		int cols = dstx.cols(), rows = dstx.rows();
		//遍历sobel图像像素点
		for(int i=0;i<cols;i++) {
			for(int j=0;j<rows;j++) {
				dstx.get(i,j,data);
				datax=fabs(data[0]);
				dsty.get(i,j,data);
				datay=fabs(data[0]);
				//大于阈值的像素点累加
				if(datax+datay>thesh)
					value+=(datax+datay);
			}
		}
		//返回图像评价指标
		return value/cols/rows;
	}
	
	/**绝对值函数
	 * @param b 输入byte
	 * @return byte正部分
	 */
	public static int fabs(byte b) {
		if(b<0)
			return -b;
		return b;
	}
}
