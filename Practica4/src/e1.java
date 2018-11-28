import java.util.*;
import java.util.stream.*;

public class e1 {
	public static void main(String[] arg) {
		int[] list = new int[] {-3,-1,0,1,2,5,10,12};
		System.out.println(buscaPosicion(list));
	}
	//Iterativo
	
	public static int buscaPosicion(int[] a) {
		int i =0;
		int j =a.length;
		int k = (i+j)/2;
		while(a[k]!= k) {
			if(k<a[k]) {
				j=k;
			}else {
				i=k+1;
			}
			k=(i+j)/2;
		}
			return k;
	}
	
	//Recursivo
	
	public static int buscaPosicionRec(int[] a) {
		int i = 0;
		int j = a.length;
		int k = a.length/2;
		return buscaPosicionRecAux(a, i, j, k);
	}
	
	private static int buscaPosicionRecAux (int[] a, int i, int j, int k) {
		int res;
		if (a[k]==k) {
			res=k;
		}else if(a[k]>k) {
			res = buscaPosicionRecAux (a, i, k, (i+k)/2 );
		}else {
			res = buscaPosicionRecAux (a, k+1, j, (j+1+k)/2 );
		}
		return res;
	}
	
	public static class Aux{
		int i;
		int j;
		int k;
		public Aux(int i, int j, int k) {
			super();
			this.i=i;
			this.j=j;
			this.k=k;
			
		}
		public static Aux create(int i, int j, int k) {
			return new Aux(i,j,k);
		}
	}
	
	public static Aux siguientebps(Aux x, int[]  a) {
		int i = x.i;
		int j = x.j;
		int k = x.k;
		if(k<a[k]) {
			x = Aux.create(i, k, (i+k)/2);
		}else {
			x = Aux.create(k+1, j, (k+1+j)/2);
		}
		return x;
		
	}
	
	public static int buscaPosicionStream(int[] a) {
		int i = 0;
		int j = a.length;
		int k = (i+j)/2;
		Aux inicial = Aux.create(i, j, k);
		return Stream.iterate(inicial, x->siguientebps(x, a))
				.dropWhile(x-> a[x.k]!= x.k)
				.findFirst()
				.get().k;
	}

}
