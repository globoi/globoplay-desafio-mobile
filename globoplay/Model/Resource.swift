//
//  Resource.swift
//  globoplay
//
//  Created by Marcos Curvello on 17/04/20.
//  Copyright © 2020 Marcos Curvello. All rights reserved.
//

import Foundation
import Combine
import TinyNetworking

enum ResourceError: Error {
    case description(code: Int, info: ErrorInfo?)
    case unknown(error: Error)
}

extension ResourceError {
    var message: String {
        switch self {
        case let .description(_,info): return info?.status_message ?? ""
        default: return "Uncataloged Error"
        }
    }
}

struct ErrorInfo: Codable {
    var status_code: Int
    var status_message: String
}

final class Resource<A>: ObservableObject {
    @Published var value: A? = nil
    @Published var error: ResourceError? = nil
    
    let endpoint: Endpoint<A>
    var cancellable: AnyCancellable?
    
    init(endpoint: Endpoint<A>) {
        self.endpoint = endpoint
        self.cancellable = URLSession.shared.load(endpoint)
            .receive(on: DispatchQueue.main)
            .mapError { error -> ResourceError in
                switch (error) {
                case let error as WrongStatusCodeError:
                    guard let body = error.responseBody else { return .unknown(error: error) }
                    let info = try? JSONDecoder().decode(ErrorInfo.self, from: body)
                    return .description(code: error.statusCode, info: info)
                default: return .unknown(error: error)
                }
        }
        .sink(receiveCompletion: { completion in
            switch completion {
            case .finished:
                break
            case let .failure(error):
                self.error = error
            }
        }, receiveValue: { value in
            self.value = value
        })
    }
}
