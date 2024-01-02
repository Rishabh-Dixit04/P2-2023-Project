#include "Sharpen.h"

#include <vector>
#include "../Pixel.h"
using namespace std;

void applySharpen(vector<vector<Pixel> > &imageVector, float amount){
    int h=imageVector.size();           //Iterating through all pixels
    int w=imageVector[0].size();
    for (long i = 1; i < h - 1; i++) {
            for (long j = 1; j < w - 1; j++) {
                Pixel Color;
                float factor =amount/50;
                // Calculate the sharpened pixel values
                int newR = imageVector[i][j].r + static_cast<int>((imageVector[i][j].r - imageVector[i - 1][j].r) * factor );
                int newG = imageVector[i][j].g + static_cast<int>((imageVector[i][j].g - imageVector[i - 1][j].g) * factor);
                int newB = imageVector[i][j].b + static_cast<int>((imageVector[i][j].b - imageVector[i - 1][j].b) * factor);

                // Clamp the values to the valid range [0, 255]
                newR = std::max(0, std::min(newR, 255));
                newG = std::max(0, std::min(newG, 255));
                newB = std::max(0, std::min(newB, 255));

                // Update the pixel in the image vector
                imageVector[i][j].r = newR;
                imageVector[i][j].g = newG;
                imageVector[i][j].b = newB;
            }
        }
}