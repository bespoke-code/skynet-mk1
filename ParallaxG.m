fid = fopen('Koord.txt');
a = fscanf(fid,'%g %g',[2 inf]) % It has two rows now.
fclose(fid)
A=a(1,:)
B=a(2,:)
Ba=sqrt((A(1,1)-B(1,1))^2 + (A(1,2)-B(1,2))^2)
D=(Ba*57.3)/(A1(1,3) + A2(1,3))
