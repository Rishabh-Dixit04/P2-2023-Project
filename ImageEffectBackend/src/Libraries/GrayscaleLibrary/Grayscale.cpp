#include "Grayscale.h"
#include <vector>
#include "../Pixel.h"
using namespace std;


void applyGrayscale(vector< vector<Pixel> > &imageVector, long h, long w){
    
    for (long y=0;y<h;y++){
        for (long x=0;x<w;x++){
            Pixel color;
            color.r =  0.2126 * imageVector[y][x].r + 0.7152 * imageVector[y][x].g + 0.0722 * imageVector[y][x].b;
            color.g =  0.2126 * imageVector[y][x].r + 0.7152 * imageVector[y][x].g + 0.0722 * imageVector[y][x].b;
            color.b = 0.2126 * imageVector[y][x].r + 0.7152 * imageVector[y][x].g + 0.0722 * imageVector[y][x].b;
            imageVector[y][x] = color;
        }
    }
}