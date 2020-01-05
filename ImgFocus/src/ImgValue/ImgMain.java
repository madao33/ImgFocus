/**主程序文件
 * @author madao
 * @creat time 2020年1月4日19:11:45
 * @ver 1.0
 */
package ImgValue;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
public class ImgMain {
	//主方法
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String filepath = "C:\\Users\\14345\\Documents\\codes\\java\\ImgFocus\\src\\ImgValue\\pics\\";
		String filename1 = "X=", filename2 = ".png";
		int[] values = {0,1,2,3,4,5,6,7,8,9,10,12,14,16,18,20,25,30,40,46};
		double p=0.0;
		for(int i=0;i<values.length;i++) {
		//读取待处理图像
		Mat src = Imgcodecs.imread(filepath + filename1 + String.valueOf(values[i]) + filename2);
		p=ImgEva.ImgValue(src, 25);
		System.out.print(p+",");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("运行时间:" + (endTime - startTime) + "ms");
	}

}
