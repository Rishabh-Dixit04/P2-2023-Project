#include "GaussianBlur.h"
#include <vector>
#include "../Pixel.h"
using namespace std;

void applyGaussianBlur(vector<vector<Pixel>>& image, float amount) {
    int size = amount;
    if(amount<=3) size =3;    
    else if(!amount&1) size=amount-1; 

    int width = image[0].size();
    int height = image.size();
    vector<float> kernel;
    for (int i = -size / 2; i <= size / 2; ++i) {   
        float value = exp(-(i * i) / (2 * pow(size / 2.0, 2))) / (sqrt(2 * M_PI) * (size / 2.0));   //Gaussian function
        kernel.push_back(value);
    }

    
    float sum = 0;          //Normalization
    for (float value : kernel) {      
        sum += value;
    }
    for (float& value : kernel) {
        value /= sum;
    }


//Applying Convolution seperately horizontally and vertically
    for (int y = 0; y < height; ++y) {
        for (int x = 0; x < width; ++x) {               //Iterating over each pixel
            Pixel result;
            result.r = result.b = result.g = 0;   //initializing pixel parameters

            for (int i = -size / 2; i <= size / 2; ++i) {           //Convolution of the 2 matrices
                int x_offset =  max(0,  min(x + i, width - 1));
                int y_offset =  max(0,  min(y + i, height - 1));

                result.r += kernel[i + size / 2] * image[y_offset][x].r;   
                result.g += kernel[i + size / 2] * image[y_offset][x].g;
                result.b += kernel[i + size / 2] * image[y_offset][x].b;
            }

            image[y][x] = result;
        }
    }
    
    for (int y = 0; y < height; ++y) {
        for (int x = 0; x < width; ++x) {
            Pixel result;
            result.r = result.b = result.g = 0;

            for (int i = -size / 2; i <= size / 2; ++i) {
                int y_offset =  max(0,  min(y + i, height - 1)); 
                int x_offset =  max(0,  min(x + i, width - 1));

                result.r += kernel[i + size / 2] * image[y][x_offset].r;   
                result.g += kernel[i + size / 2] * image[y][x_offset].g;
                result.b += kernel[i + size / 2] * image[y][x_offset].b;
            }

            image[y][x] = result;
        }
    }
}