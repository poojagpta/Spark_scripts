def sparse_matrix_multiplication(A, B):
  rows,cols = (len(A),len(B[0]))
  arr = [[0]*cols]*rows
  for rA in range(0, len(A)): 
    rowA= A[rA]
    l=[]
    for cb in range(0, len(B[0])):
      colB = [row[cb] for row in B]
      sum=0
      for i in range(0, len(rowA)):
        sum=sum+rowA[i]*colB[i]
      l.append(sum)
    arr[rA]=l
  return arr  

A = [ [ 1, 0, 0], [-1, 0, 3] ]
B = [ [ 7, 0, 0 ], [ 0, 0, 0 ], [ 0, 0, 1 ] ]

sparse_matrix_multiplication(A,B)
