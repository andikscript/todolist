<?php

namespace App\Http\Controllers;

use App\Models\Todo;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;

class TodoController extends Controller {
    public function get(){
        $nmData = Todo::orderByDesc('name')->get();
        $data =[
            'success'=> 1,
            'message' => 'Berhasil',
            'todoses' => $nmData
        ];
        return $data;
    }

    public function create(Request $request){
        $validasi = Validator::make($request->all(),[]);

        if ($validasi->fails()){
            $val = $validasi->errors()->all();
            return $this->error($val[0]);
        }
        $nmData = Todo::create($request->all());

        if($nmData){
            return response()->json([
            'success' =>1,
            'message' =>'Todo List berhasil dibuat',
            'todoses' =>$nmData
            ]
            );
        }
        return $this->error('Gagal membuat list');
    }

    public function update (Request $request, $id){
        $nmData = Todo::where('id', $id)->first();
        if($nmData){
            $nmData->update($request->all());
        }

        if($nmData){
            return response()->json([
                'success' => 1,
                'message' => 'Todo List berhasil dibuat',
                'todoses'=>$nmData
            ]);
        }
        return $this->error('Gagal merubah data');
    }

    public function delete($id){
        $kelas = Todo::where('id', $id->first());

        if($kelas){
            $kelas->delete();
            $nmList = Todo::all();
            return response()->json([
                'success'=>1,
                'message' =>'Todo List berhasil dihapus',
                'todoses' => $nmList
            ]);
        }
        return $this->error('Data tidak ditemukan');
    }

    public function error($pesan){
        return response()->json([
            'success'=>0,
            'message'=> $pesan
        ]);
    }
}
