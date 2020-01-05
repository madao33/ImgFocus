/**ͼ������
 * @author madao
 * @time 2020��1��5��10:55:00
 * @ver 1.0
 */
package ImgValue;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

public class ImgEva {
	/**ͼ�����۵��ú���
	 * @param src-mat��������ԭʼͼ��
	 * @return value-doubel���ͣ�ͼ������ָ�꣬�Խ�Խ׼ȷ��ֵԽ��
	 */
	public static double ImgValue(Mat src, double thresh) {
		Mat gray = src.clone();
		Mat dstx = src.clone();
        Mat dsty = src.clone();
		 //�ҶȻ�����ͼƬ
		Imgproc.cvtColor(src,gray,Imgproc.COLOR_RGB2GRAY);

		//X����sobel����
		Imgproc.Sobel(gray, dstx, -1, 1, 0, 3, 1, 0, Core.BORDER_DEFAULT);
//		HighGui.imshow("X����", dstx);
//		HighGui.waitKey(0);
		//Y����sobel����
		Imgproc.Sobel(gray, dsty, -1, 0, 1, 3, 1, 0, Core.BORDER_DEFAULT);
//		HighGui.imshow("Y����", dsty);
//		HighGui.waitKey(0);
		
		Core.addWeighted(dstx, 0.5, dsty, 0.5, 0, gray);
//		HighGui.imshow("sobel���Ӵ����ͼ��", gray);
//		HighGui.waitKey(0);
		double value = mean(dstx,dsty, thresh);
//		System.out.print(value + "\n");
		return value;
	}
	
	/**sobel���ӶԽ����ۺ���
	 * @author: madao
 	 * @param:dstx-x����sobel�����ͼ��dsty-y����sobel�����ͼ��thresh-����������ֵ��Ӱ��Խ�����
	 * @return��
	 */
	private static double mean(Mat dstx, Mat dsty, double thesh) {
		// TODO Auto-generated method stub
		int datax = 0, datay = 0;
		byte[] data = new byte[dstx.channels()];
		double value = 0;
		int cols = dstx.cols(), rows = dstx.rows();
		//����sobelͼ�����ص�
		for(int i=0;i<cols;i++) {
			for(int j=0;j<rows;j++) {
				dstx.get(i,j,data);
				datax=fabs(data[0]);
				dsty.get(i,j,data);
				datay=fabs(data[0]);
				//������ֵ�����ص��ۼ�
				if(datax+datay>thesh)
					value+=(datax+datay);
			}
		}
		//����ͼ������ָ��
		return value/cols/rows;
	}
	
	/**����ֵ����
	 * @param b ����byte
	 * @return byte������
	 */
	public static int fabs(byte b) {
		if(b<0)
			return -b;
		return b;
	}
}
