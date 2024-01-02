#include "Contrast.h"
#include <vector>
#include "../Pixel.h"

using namespace std;

void applyContrast(vector<vector<Pixel> > &img, float amount){      
    amount = amount/100;
    for (auto &row: img){       //Iterating through all pixels and changing contrast amount
        for (Pixel &p: row){
            p.r = (amount * (p.r - 128)) + 128;
            p.g = (amount * (p.g - 128)) + 128;
            p.b = (amount * (p.b - 128)) + 128;
            if(p.r > 255) p.r = 255;        
            if(p.g > 255) p.g = 255;        //Clamping values to ensure they are in valid range
            if(p.b > 255) p.b = 255;
            if(p.r < 0) p.r = 0;
            if(p.g < 0) p.g = 0;
            if(p.b < 0) p.b = 0;
        }
    }
}