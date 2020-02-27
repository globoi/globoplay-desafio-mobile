//
//  Manager.swift
//  TheMovieDB
//
//  Created by Edwy Lugo on 26/02/20.
//  Copyright © 2020 Edwy Lugo. All rights reserved.
//

import Foundation

let plistFileName:String = "extraInfo"

struct Plist {
    
    enum PlistError: Error {
        case FileNotWritten
        case FileDoesNotExist
    }
    
    let name:String
    
    var sourcePath:String? {
        guard let path = Bundle.main.path(forResource: name, ofType: "plist") else { return .none }
        return path
    }
    
    var destPath:String? {
        guard sourcePath != .none else { return .none }
        let dir = NSSearchPathForDirectoriesInDomains(.documentDirectory, .userDomainMask, true)[0]
        return (dir as NSString).appendingPathComponent("\(name).plist")
    }
    
    init?(name:String) {
        
        self.name = name
        
        let fileManager = FileManager.default
        
        guard let source = sourcePath else { return nil }
        guard let destination = destPath else { return nil }
        guard fileManager.fileExists(atPath: source) else { return nil }
        
        if !fileManager.fileExists(atPath: destination) {
            
            do {
                try fileManager.copyItem(atPath: source, toPath: destination)
            } catch let error as NSError {
                return nil
            }
        }
    }
    
    func getValuesInPlistFile() -> NSDictionary?{
        let fileManager = FileManager.default
        if fileManager.fileExists(atPath: destPath!) {
            guard let dict = NSDictionary(contentsOfFile: destPath!) else { return .none }
            return dict
        } else {
            return .none
        }
    }
    
    func getMutablePlistFile() -> NSMutableDictionary?{
        let fileManager = FileManager.default
        if fileManager.fileExists(atPath: destPath!) {
            guard let dict = NSMutableDictionary(contentsOfFile: destPath!) else { return .none }
            return dict
        } else {
            return .none
        }
    }
    
    func addValuesToPlistFile(dictionary:NSDictionary) throws {
        let fileManager = FileManager.default
        if fileManager.fileExists(atPath: destPath!) {
            if !dictionary.write(toFile: destPath!, atomically: false) {
                //print("[PlistManager] File not written successfully")
                throw PlistError.FileNotWritten
            }
        } else {
            throw PlistError.FileDoesNotExist
        }
    }
    
}

class PlistManager {
    static let sharedInstance = PlistManager()
    private init() {}
    
    func startPlistManager() {
        if let _ = Plist(name: plistFileName) {
        }
    }
    
    func addNewItemWithKey(key:String, value:AnyObject) {
        if !keyAlreadyExists(key: key) {
            if let plist = Plist(name: plistFileName) {
                
                let dict = plist.getMutablePlistFile()!
                dict[key] = value
                
                do {
                    try plist.addValuesToPlistFile(dictionary: dict)
                } catch {
                    print(error)
                }
            
            } else {
            }
        } else {
        }
        
        
    }
    
    func removeItemForKey(key:String) {
        if keyAlreadyExists(key: key) {
            if let plist = Plist(name: plistFileName) {
                
                let dict = plist.getMutablePlistFile()!
                dict.removeObject(forKey: key)
                
                do {
                    try plist.addValuesToPlistFile(dictionary: dict)
                } catch {
                    print(error)
                }
                
            } else {
                
            }
        } else {
            
        }
        
    }
    
    func removeAllItemsFromPlist() {
        
        if let plist = Plist(name: plistFileName) {
            
            let dict = plist.getMutablePlistFile()!
            
            let keys = Array(dict.allKeys)
            
            if keys.count != 0 {
                dict.removeAllObjects()
            } else {
                
            }
            
            do {
                try plist.addValuesToPlistFile(dictionary: dict)
            } catch {
                print(error)
            }
        } else {

        }
    }
    
    func saveValue(value: Any, forKey:String) {
        
        if let plist = Plist(name: plistFileName) {
            
            let dict = plist.getMutablePlistFile()!
            
            if let dictValue = dict[forKey] {
                
                if type(of: value) != type(of: dictValue) {
                }
                
                dict[forKey] = value
                
            }
            
            do {
                try plist.addValuesToPlistFile(dictionary: dict)
            } catch {
                print(error)
            }
        } else {

        }
    }
    
    func getValueForKey(key:String) -> AnyObject? {
        var value:AnyObject?
        
        
        if let plist = Plist(name: plistFileName) {
            
            let dict = plist.getMutablePlistFile()!
            
            let keys = Array(dict.allKeys)
            
            if keys.count != 0 {
                
                for (_,element) in keys.enumerated() {
                    if element as! String == key {
                        value = dict[key]! as AnyObject
                    } else {
                        
                    }
                }
                
                if value != nil {
                    return value!
                } else {
                    return .none
                }
                
            } else {

                return .none
            }
    
        } else {
            return .none
        }
        
    }
    
    func keyAlreadyExists(key:String) -> Bool {
        var keyExists = false
        
        if let plist = Plist(name: plistFileName) {
            
            let dict = plist.getMutablePlistFile()!
            
            let keys = Array(dict.allKeys)
            if keys.count != 0 {
                
                for (_,element) in keys.enumerated() {
                    
                    if element as! String == key {
                    
                        keyExists = true
                    } else {
                    
                    }
                }
                
            } else {
                keyExists =  false
            }
        
        } else {
            keyExists = false
        }
        return keyExists
    }
}
