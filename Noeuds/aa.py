def  f(x):
     y=x**3+x**2-10
     return(y)

x=0
h=0,1
a=f(x)
while a<0:
  x=x+h
  a=f(x)

     
