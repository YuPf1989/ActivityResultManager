# ActivityResultManager
使用统一的方式调用startActivityForResult(),onActivityResult(),该demo用于练习通过fragment的方式处理activity的生命周期，包括Glide、Rxjava都
使用了相似的处理技巧

### 原理
`ActivityResultManager`内部维护了一个无界面的fragment，fragment与发起调用`startActivityForResult`的activity绑定，进而将调用交
由fragment处理，将结果通过回调的方式返回给`ActivityResultManager`

### 使用
```
findViewById(R.id.btn_start_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                new ActivityResultManager(MainActivity.this)
                        .startForResult(SecondActivity.class, 0, new IResultCallback() {
                            @Override
                            public void getResultCallback(int requestCode, int resultCode, Intent data) {
                                String s = data.getStringExtra("data");
                                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }); 
```

### 参考
https://github.com/AnotherJack/AvoidOnResult

感谢原作者，这里作者给了3种方案，最为优雅的当属采用fragment这种
