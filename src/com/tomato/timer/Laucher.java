package com.tomato.timer;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class Laucher {

	private FileChannel channel;

	private FileLock lock;

	private File file;

	public Laucher(String lockFileName) {
		file = new File(lockFileName);
	}

	public boolean isAppActive() {
		try {
			channel = new RandomAccessFile(file, "rw").getChannel();
			try {
				lock = channel.tryLock();
			} catch (OverlappingFileLockException e) {
				closeLock();
				return true;
			}
			if (lock == null) {
				closeLock();
				return true;
			}
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					closeLock();
					deleteFile();
				}
			});
			return false;
		} catch (Exception e) {
			closeLock();
			return true;
		}
	}

	protected void deleteFile() {
		try {
			file.delete();
		} catch (Exception e) {

		}
	}

	private void closeLock() {
		try {
			lock.release();
		} catch (Exception e) {

		}
		try {
			channel.close();
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
//		Laucher a = new Laucher(".lock");
//		if (a.isAppActive() == false) {
//			MainFrame.createApp();
//		}
		
		System.out.println(4099-3589);
	}
}
