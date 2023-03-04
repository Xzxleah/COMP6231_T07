

import mpi.*;
public class MPJSendReceive{
	public static void main(String[] args) throws Exception{
		if (args.length < 5) {
			System.out.println("usage: $MPJ_HOME/bin/mpjrun.sh -np 2 -cp output MPJSendReceive <integer> <integer> ");
			return;
		}

		MPI.Init(args);
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();
		int tag = 1;
		int dst;


		if(rank == 1){
			dst = 0;
			int buffer_rcv[] = new int [1];
			int buffer_snt[] = new int [2];
			buffer_snt[0] = Integer.parseInt(args[4]);
			buffer_snt[1] = Integer.parseInt(args[3]);
			MPI.COMM_WORLD.Send(buffer_snt, 0, 2, MPI.INT, dst, tag);
			System.out.println("My ID is <" + rank +"> sent # " + buffer_snt[0]);
			//System.out.println("My ID is <" + rank +"> sent # " + buffer_snt[1]);
			//TODO: implment Receive of the sum
			MPI.COMM_WORLD.Recv(buffer_rcv, 0, 1, MPI.INT, dst, tag);
			System.out.println("My ID is <" + rank +"> sum is " + buffer_rcv[0]);

		}

		else if(rank == 0){
			dst = 1;
			int buffer_snt[] = new int [1];
			int buffer_rcv[] = new int [2];
			MPI.COMM_WORLD.Recv(buffer_rcv, 0, 2, MPI.INT, dst, tag);
			System.out.println("My ID is <" + rank +"> received # " + buffer_rcv[0]);
			//System.out.println("My ID is <" + rank +"> received # " + buffer_rcv[1]);
			//TODO implement addition of given integers
			int sum = buffer_rcv[0] + buffer_rcv[1];

			//TODO implement Send of the sum.
			buffer_snt[0] = sum;
			MPI.COMM_WORLD.Send(buffer_snt, 0, 1, MPI.INT, dst, tag);
			System.out.println("My ID is <" + rank +"> sent sum " + buffer_snt[0]);
		}

		else{
			System.out.println("My ID is <" + rank +">. I'm noy doing anything");
		}
		MPI.Finalize();
	}
}


