#include "../Pixel.h"
#include "Sepia.h"
#include <vector>
using namespace std;

void applySepia(vector< vector<Pixel> > &imageVector, long h, long w){
    for (long i = 0; i < h; i++) {      //Iterating through all pixels and applying Sepia
        for (long j = 0; j < w; j++) {
            Pixel color;
            color.r = max(min(static_cast<int>((imageVector[i][j].r * 0.393) + (imageVector[i][j].g * 0.769) + (imageVector[i][j].b * 0.189)), 255), 0);
            color.g = max(min(static_cast<int>((imageVector[i][j].r * 0.349) + (imageVector[i][j].g * 0.686) + (imageVector[i][j].b * 0.168)), 255), 0);
            color.b = max(min(static_cast<int>((imageVector[i][j].r * 0.272) + (imageVector[i][j].g * 0.534) + (imageVector[i][j].b * 0.131)), 255), 0);

            imageVector[i][j] = color;
        }
    }
}

