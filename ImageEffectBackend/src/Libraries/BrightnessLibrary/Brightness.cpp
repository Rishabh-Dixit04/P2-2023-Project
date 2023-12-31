#include "Brightness.h"
#include <vector>
#include "../Pixel.h"
using namespace std;

void applyBrightness(vector<vector<Pixel> > &img, float amount){
    amount = 2.55 * amount - 255;
    for (auto &row: img){
        for (Pixel &p: row){
            p.r = p.r + amount;
            p.g = p.g + amount;
            p.b = p.b + amount;
            if(p.r > 255) p.r = 255;
            if(p.g > 255) p.g = 255;
            if(p.b > 255) p.b = 255;
            if(p.r < 0) p.r = 0;
            if(p.g < 0) p.g = 0;
            if(p.b < 0) p.b = 0;
        }
    }
}