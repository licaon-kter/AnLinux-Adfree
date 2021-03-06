package exa.lnx.a;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class DesktopEnvironment extends Fragment {

    Context context;
    SharedPreferences sharedPreferences;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    String distro;
    String desktop;
    String s;
    boolean isDeviceSpaceNotified;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        getActivity().setTitle(R.string.desktop_title);

        View view = inflater.inflate(R.layout.desktop_environment, container, false);

        context = getActivity().getApplicationContext();

        distro = "Nothing";
        desktop = "Nothing";

        s = Build.SUPPORTED_ABIS[0];

        sharedPreferences = context.getSharedPreferences("GlobalPreferences", 0);
        isDeviceSpaceNotified = sharedPreferences.getBoolean("IsDeviceSpaceNotified", false);

        button = view.findViewById(R.id.button);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);

        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
        textView4 = view.findViewById(R.id.textView4);

        textView2.setText("Step 2 : Please choose a distro first.");
        textView3.setText("Step 3 : Please choose a Desktop Environment first");
        textView4.setText("Step 4 : Please choose a Desktop Environment first.");
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyUserToChooseDistro();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyUserToChooseDesktop();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                if(distro.equals("Ubuntu") | distro.equals("Debian") | distro.equals("Kali") | distro.equals("Parrot")){
                    if(desktop.equals("Xfce4")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Xfce4/de-apt-xfce4.sh && bash de-apt-xfce4.sh");
                        clipboard.setPrimaryClip(clip);
                    }else if(desktop.equals("Mate")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Mate/de-apt-mate.sh && bash de-apt-mate.sh");
                        clipboard.setPrimaryClip(clip);
                    }else if(desktop.equals("LXQt")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXQt/de-apt-lxqt.sh && bash de-apt-lxqt.sh");
                        clipboard.setPrimaryClip(clip);
                    }else if(desktop.equals("LXDE")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXDE/de-apt-lxde.sh && bash de-apt-lxde.sh");
                        clipboard.setPrimaryClip(clip);
                    }
                }else if(distro.equals("Fedora")){
                    if(s.contains("arm") && !s.equals("arm64-v8a")){
                        if(desktop.equals("Xfce4")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/Xfce4/de-yum-xfce4.sh && bash de-yum-xfce4.sh");
                            clipboard.setPrimaryClip(clip);
                        }else if(desktop.equals("Mate")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/Mate/de-yum-mate.sh && bash de-yum-mate.sh");
                            clipboard.setPrimaryClip(clip);
                        }else if(desktop.equals("LXQt")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/LXQt/de-yum-lxqt.sh && bash de-yum-lxqt.sh");
                            clipboard.setPrimaryClip(clip);
                        }else if(desktop.equals("LXDE")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/LXDE/de-yum-lxde.sh && bash de-yum-lxde.sh");
                            clipboard.setPrimaryClip(clip);
                        }
                    }else{
                        if(desktop.equals("Xfce4")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/Xfce4/de-yum-xfce4.sh && bash de-yum-xfce4.sh");
                            clipboard.setPrimaryClip(clip);
                        }else if(desktop.equals("Mate")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/Mate/de-yum-mate.sh && bash de-yum-mate.sh");
                            clipboard.setPrimaryClip(clip);
                        }else if(desktop.equals("LXQt")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/LXQt/de-yum-lxqt.sh && bash de-yum-lxqt.sh");
                            clipboard.setPrimaryClip(clip);
                        }else if(desktop.equals("LXDE")){
                            ClipData clip = ClipData.newPlainText("Command", "yum install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/LXDE/de-yum-lxde.sh && bash de-yum-lxde.sh");
                            clipboard.setPrimaryClip(clip);
                        }
                    }
                }else if(distro.equals("Arch")){
                    if(s.contains("arm")){
                        ClipData clip = ClipData.newPlainText("Command", "pacman-key --init && pacman-key --populate archlinuxarm && pacman -Sy --noconfirm wget && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Pacman/de-pac.sh && bash de-pac.sh");
                        clipboard.setPrimaryClip(clip);
                    }else{
                        ClipData clip = ClipData.newPlainText("Command", "pacman-key --init && pacman-key --populate archlinux && pacman -Sy --noconfirm wget && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Pacman/de-pac.sh && bash de-pac.sh");
                        clipboard.setPrimaryClip(clip);
                    }
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.termux");
                if(isPackageInstalled("com.termux", context.getPackageManager())){
                    startActivity(intent);
                }else{
                    notifyUserForInstallTerminal();
                }
            }
        });
        if(!isDeviceSpaceNotified){
            notifyUserForDeviceSpace();
        }

        return view;
    }
    public void notifyUserToChooseDistro(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.desktop_environment_chooser, nullParent);
        final CheckBox checkBox = view.findViewById(R.id.checkBox);
        final CheckBox checkBox2 = view.findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = view.findViewById(R.id.checkBox3);
        final CheckBox checkBox4 = view.findViewById(R.id.checkBox4);
        final CheckBox checkBox5 = view.findViewById(R.id.checkBox5);
        final CheckBox checkBox6 = view.findViewById(R.id.checkBox6);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);

        if(distro.equals("Ubuntu")){
            checkBox.setChecked(true);
        }else if(distro.equals("Debian")){
            checkBox2.setChecked(true);
        }else if(distro.equals("Kali")){
            checkBox3.setChecked(true);
        }else if(distro.equals("Parrot")){
            checkBox4.setChecked(true);
        }else if(distro.equals("Fedora")){
            checkBox5.setChecked(true);
        }else if(distro.equals("Arch")){
            checkBox6.setChecked(true);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
            }
        });
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox6.setChecked(false);
            }
        });
        checkBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
            }
        });
        if(s.equals("i386")){
            checkBox5.setEnabled(false);
            checkBox5.setText("Not supported");
            checkBox6.setEnabled(false);
            checkBox6.setText("Not supported");
        }
        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(checkBox.isChecked()){
                    if(!distro.equals("Ubuntu")){
                        distro = "Ubuntu";
                    }
                }else if(checkBox2.isChecked()){
                    if(!distro.equals("Debian")){
                        distro = "Debian";
                    }
                }else if(checkBox3.isChecked()){
                    if(!distro.equals("Kali")){
                        distro = "Kali";
                    }
                }else if(checkBox4.isChecked()){
                    if(!distro.equals("Parrot")){
                        distro = "Parrot";
                    }
                }else if(checkBox5.isChecked()){
                    if(!distro.equals("Fedora")){
                        distro = "Fedora";
                    }
                }else if(checkBox6.isChecked()){
                    if(!distro.equals("Arch")){
                        distro = "Arch";
                    }
                }
                textView2.setText("Step 2: Please choose a Desktop Environment");
                button2.setEnabled(true);
                textView3.setText("Step 3 : Please choose a Desktop Environment first");
                textView4.setText("Step 4 : Please choose a Desktop Environment first.");
                button3.setEnabled(false);
                button4.setEnabled(false);
                desktop = "Nothing";
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public void notifyUserToChooseDesktop(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.desktop_environment_chooser2, nullParent);
        final CheckBox checkBox = view.findViewById(R.id.checkBox);
        final CheckBox checkBox2 = view.findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = view.findViewById(R.id.checkBox3);
        final CheckBox checkBox4 = view.findViewById(R.id.checkBox4);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);

        if(desktop.equals("Xfce4")){
            if(!distro.equals("Arch")){
                checkBox.setChecked(true);
            }
        }else if(desktop.equals("Mate")){
            if(!distro.equals("Arch")){
                checkBox2.setChecked(true);
            }
        }else if(desktop.equals("LXQt")){
            if(!distro.equals("Arch")){
                checkBox3.setChecked(true);
            }
        }else if(desktop.equals("LXDE")){
            checkBox4.setChecked(true);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox4.setChecked(false);
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
            }
        });
        if(distro.equals("Arch")){
            checkBox.setText("Not Available");
            checkBox2.setText("Not Available");
            checkBox3.setText("Not Available");
            checkBox.setEnabled(false);
            checkBox2.setEnabled(false);
            checkBox3.setEnabled(false);
        }
        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(checkBox.isChecked()){
                    if(!desktop.equals("Xfce4")){
                        desktop = "Xfce4";
                    }
                }else if(checkBox2.isChecked()){
                    if(!desktop.equals("Mate")){
                        desktop = "Mate";
                    }
                }else if(checkBox3.isChecked()){
                    if(!desktop.equals("LXQt")){
                        desktop = "LXQt";
                    }
                }else if(checkBox4.isChecked()){
                    if(!desktop.equals("LXDE")){
                        desktop = "LXDE";
                    }
                }
                if(distro.equals("Ubuntu")){
                    if(desktop.equals("Xfce4")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Xfce4/de-apt-xfce4.sh && bash de-apt-xfce4.sh \n\n This should setup Xfce4 and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-ubuntu.sh to run the Linux System before using this command.");
                    }else if(desktop.equals("Mate")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Mate/de-apt-mate.sh && bash de-apt-mate.sh \n\n This should setup Mate and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-ubuntu.sh to run the Linux System before using this command.");
                    }else if(desktop.equals("LXQt")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXQt/de-apt-lxqt.sh && bash de-apt-lxqt.sh \n\n This should setup LXQt and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-ubuntu.sh to run the Linux System before using this command.");
                    }else if(desktop.equals("LXDE")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXDE/de-apt-lxde.sh && bash de-apt-lxde.sh \n\n This should setup LXDE and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-ubuntu.sh to run the Linux System before using this command.");
                    }
                }else if(distro.equals("Debian")){
                    if(desktop.equals("Xfce4")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Xfce4/de-apt-xfce4.sh && bash de-apt-xfce4.sh \n\n This should setup Xfce4 and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-debian.sh to run the Linux System before using this command.");
                    }else if(desktop.equals("Mate")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Mate/de-apt-mate.sh && bash de-apt-mate.sh \n\n This should setup Mate and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-debian.sh to run the Linux System before using this command.");
                    }else if(desktop.equals("LXQt")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXQt/de-apt-lxqt.sh && bash de-apt-lxqt.sh \n\n This should setup LXQt and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-debian.sh to run the Linux System before using this command.");
                    }else if(desktop.equals("LXDE")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXDE/de-apt-lxde.sh && bash de-apt-lxde.sh \n\n This should setup LXDE and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-debian.sh to run the Linux System before using this command.");
                    }
                }else if(distro.equals("Kali")){
                    if(desktop.equals("Xfce4")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Xfce4/de-apt-xfce4.sh && bash de-apt-xfce4.sh \n\n This should setup Xfce4 and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-kali.sh to run the Linux System before using this command.");
                    }else if(desktop.equals("Mate")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Mate/de-apt-mate.sh && bash de-apt-mate.sh \n\n This should setup Mate and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-kali.sh to run the Linux System before using this command.");
                    }else if(desktop.equals("LXQt")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXQt/de-apt-lxqt.sh && bash de-apt-lxqt.sh \n\n This should setup LXQt and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-kali.sh to run the Linux System before using this command.");
                    }else if(desktop.equals("LXDE")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXDE/de-apt-lxde.sh && bash de-apt-lxde.sh \n\n This should setup LXDE and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-kali.sh to run the Linux System before using this command.");
                    }
                }else if(distro.equals("Parrot")){
                    if(desktop.equals("Xfce4")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Xfce4/de-apt-xfce4.sh && bash de-apt-xfce4.sh \n\n This should setup Xfce4 and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-parrot.sh to run the Linux System before using this command.");
                    }else if(desktop.equals("Mate")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/Mate/de-apt-mate.sh && bash de-apt-mate.sh \n\n This should setup Mate and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-parrot.sh to run the Linux System before using this command.");
                    }else if(desktop.equals("LXQt")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXQt/de-apt-lxqt.sh && bash de-apt-lxqt.sh \n\n This should setup LXQt and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-parrot.sh to run the Linux System before using this command.");
                    }else if(desktop.equals("LXDE")){
                        textView3.setText("Step 2 : Copy the command to clipboard : apt-get update && apt-get install wget -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Apt/LXDE/de-apt-lxde.sh && bash de-apt-lxde.sh \n\n This should setup LXDE and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-parrot.sh to run the Linux System before using this command.");
                    }
                }else if(distro.equals("Fedora")){
                    if(s.contains("arm") && !s.equals("arm64-v8a")){
                        if(desktop.equals("Xfce4")){
                            textView3.setText("Step 2 : Copy the command to clipboard : yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/Xfce4/de-yum-xfce4.sh && bash de-yum-xfce4.sh \n\n This should setup Xfce4 and Tiger VNC on the Linux System.");
                            textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-fedora.sh to run the Linux System before using this command.");
                        }else if(desktop.equals("Mate")){
                            textView3.setText("Step 2 : Copy the command to clipboard : yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/Mate/de-yum-mate.sh && bash de-yum-mate.sh \n\n This should setup Mate and Tiger VNC on the Linux System.");
                            textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-fedora.sh to run the Linux System before using this command.");
                        }else if(desktop.equals("LXQt")){
                            textView3.setText("Step 2 : Copy the command to clipboard : yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/LXQt/de-yum-lxqt.sh && bash de-yum-lxqt.sh \n\n This should setup LXQt and Tiger VNC on the Linux System.");
                            textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-fedora.sh to run the Linux System before using this command.");
                        }else if(desktop.equals("LXDE")){
                            textView3.setText("Step 2 : Copy the command to clipboard : yum install wget --forcearch=armv7hl -y && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/arm/LXDE/de-yum-lxde.sh && bash de-yum-lxde.sh \n\n This should setup LXDE and Tiger VNC on the Linux System.");
                            textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-fedora.sh to run the Linux System before using this command.");
                        }
                    }else{
                        if(desktop.equals("Xfce4")){
                            textView3.setText("Step 2 : Copy the command to clipboard : yum install wget -y &&  wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/Xfce4/de-yum-xfce4.sh && bash de-yum-xfce4.sh \n\n This should setup Xfce4 and Tiger VNC on the Linux System.");
                            textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-fedora.sh to run the Linux System before using this command.");
                        }else if(desktop.equals("Mate")){
                            textView3.setText("Step 2 : Copy the command to clipboard : yum install wget -y &&  wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/Mate/de-yum-mate.sh && bash de-yum-mate.sh \n\n This should setup Mate and Tiger VNC on the Linux System.");
                            textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-fedora.sh to run the Linux System before using this command.");
                        }else if(desktop.equals("LXQt")){
                            textView3.setText("Step 2 : Copy the command to clipboard : yum install wget -y &&  wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/LXQt/de-yum-lxqt.sh && bash de-yum-lxqt.sh \n\n This should setup LXQt and Tiger VNC on the Linux System.");
                            textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-fedora.sh to run the Linux System before using this command.");
                        }else if(desktop.equals("LXDE")) {
                            textView3.setText("Step 2 : Copy the command to clipboard : yum install wget -y &&  wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Yum/Fedora/LXDE/de-yum-lxde.sh && bash de-yum-lxde.sh \n\n This should setup LXDE and Tiger VNC on the Linux System.");
                            textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-fedora.sh to run the Linux System before using this command.");
                        }
                    }
                }else if(distro.equals("Arch")){
                    if(s.contains("arm")){
                        textView3.setText("pacman-key --init && pacman-key --populate archlinuxarm && pacman -Sy --noconfirm wget && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Pacman/de-pac.sh && bash de-pac.sh \n\n This should setup LXDE and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-arch.sh to run the Linux System before using this command.");
                    }else{
                        textView3.setText("pacman-key --init && pacman-key --populate archlinux && pacman -Sy --noconfirm wget && wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Scripts/DesktopEnvironment/Pacman/de-pac.sh && bash de-pac.sh \n\n This should setup LXDE and Tiger VNC on the Linux System.");
                        textView4.setText("Step 3 : Start Termux, paste and enter the command to install the desktop environment. Remember: you will need to run ./start-arch.sh to run the Linux System before using this command.");
                    }
                }
                button3.setEnabled(true);
                button4.setEnabled(true);
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public void notifyUserForInstallTerminal(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.notify1, nullParent);
        TextView textView = view.findViewById(R.id.textView);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("market://details?id=com.termux");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if(Build.VERSION.SDK_INT >= 21){
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                }
                try{
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.termux")));
                }
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
        textView.setText("Termux is not installed, do you want to install it now ?");
    }
    public void notifyUserForDeviceSpace(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.notify1, nullParent);
        TextView textView = view.findViewById(R.id.textView);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("IsDeviceSpaceNotified", true);
                editor.apply();
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Fragment fragment = new DashBoard();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentHolder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                dialog.dismiss();
            }
        });
        alertDialog.show();
        textView.setText("IMPORTANT\n\n\nPlease take a look at below warning patiently:\n\n1. Installing Desktop Environment will take around 1 GB space, 1.5 GB space is recommended.\n\n2. NEVER install two Desktop Environment on a same distro, if you want to install another Desktop Environment, please uninstall the distro using \"Uninstall\" feature first.\n\n\nDo you agree to proceed ?");
    }
    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
