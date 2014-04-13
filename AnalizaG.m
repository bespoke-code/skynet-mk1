I1=imread('1.jpg');
I2=imread('2.jpg');
I3=imread('3.jpg');
%imshow(I1)
%figure, imshow(I2)
%figure, imshow(I3)

[m,n,r]=size(I1);
i=0;
j=0;
k=0;
for k=1:r
    for i=1:m
        for j=1:n
            if (I1(i,j,k)>175)
                I1(i,j,k)=255;
            end
            if (I1(i,j,k)<174)
                I1(i,j,k)=0;
            end
        end
    end
end
%figure, imshow(I1)


for k=1:r
    for i=1:m
        for j=1:n
            if (I2(i,j,k)>175)
                I2(i,j,k)=255;
            end
            if (I2(i,j,k)<174)
                I2(i,j,k)=0;
            end
        end
    end
end
%figure, imshow(I2)



for k=1:r
    for i=1:m
        for j=1:n
            if (I3(i,j,k)>175)
                I3(i,j,k)=255;
            end
            if (I3(i,j,k)<174)
                I3(i,j,k)=0;
            end
        end
    end
end
%figure, imshow(I3)





for k=1:r
    for i=1:m
        for j=1:n
            if (I1(i,j,k)==0 & I2(i,j,k)==255)
                I2(i,j,k)=255;
            end
            if (I1(i,j,k)==255 & I2(i,j,k)==255)
                I2(i,j,k)=0;
            end
            if (I2(i,j,k)==0 & I1(i,j,k)==255)
                I2(i,j,k)=0;
            end
        end
    end
end
%figure, imshow(I2)



BW=im2bw(I2);
%figure, imshow(BW)

BW2 = bwareaopen(BW, 4);

%figure, imshow(BW2)

%IM2=BW2;


%%// Get only the comet blob, which is the biggest blob
[L, num] = bwlabel(BW2);


counts = sum(bsxfun(@eq,L(:),1:num));
[~,ind] = max(counts);
BW = (L==ind);

%%// Find the centroid of the comet blob
stats = regionprops(L==ind, 'Centroid');
center_point = stats.Centroid;
t=center_point
figure, imshow(BW)
hold on
plot(center_point(1,1),center_point(1,2),'b.','MarkerSize',12)

IM2=BW;

R=[t(1,2)-20 t(1,2)-20 t(1,2)+20 t(1,1)+20];


for k=1:r
    for i=1:m
        for j=1:n
            if (i<R(1,1) | i>R(1,3) | j<R(1,2) | j>R(1,4))
                I3(i,j,k)=0;
            end
            if (I2(i,j,k)==0 & I3(i,j,k)==255)
                I3(i,j,k)=255;
            end
            if (I2(i,j,k)==255 & I3(i,j,k)==255)
                I3(i,j,k)=0;
            end
            if (I3(i,j,k)==0 & I2(i,j,k)==255)
                I3(i,j,k)=0;
            end
        end
    end
end
%figure, imshow(I3)



I3=im2bw(I3);



[L, num] = bwlabel(I3);


counts = sum(bsxfun(@eq,L(:),1:num));
[~,ind] = max(counts);
BW = (L==ind);

%%// Find the centroid of the comet blob
stats = regionprops(L==ind, 'Centroid');
center_point = stats.Centroid;
t=center_point
figure, imshow(I3)
hold on
plot(center_point(1,1),center_point(1,2),'b.','MarkerSize',12)
