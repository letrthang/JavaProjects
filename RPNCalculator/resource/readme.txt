Assuming that input data is stored in a csv file. There are some things need to consider:

1.		Need to know how many row and column on sheet -> then we will know total how many cells.
2.		Avoid cyclic dependencies in the input data (this is an important part ).
3. 	Implement RPN algorithm with care of cyclic dependencies.

- Main idea is to use the recursive method to detect cyclic dependencies and calculate RPN.
- Please use csv file template in resource directory to run code.
