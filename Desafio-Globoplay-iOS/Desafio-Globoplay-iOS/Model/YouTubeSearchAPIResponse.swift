//
//  YouTubeSearchAPIResponse.swift
//  Desafio-Globoplay-iOS
//
//  Created by Gáudio Ney on 27/02/22.
//

import Foundation

// MARK: - YouTubeSearchAPIResult
struct YouTubeSearchAPIResponse: Codable {
    let kind, etag, nextPageToken, regionCode: String?
    let pageInfo: PageInfo?
    let items: [YouTubeVideoItem]?
}

// MARK: - YouTubeVideoItem
struct YouTubeVideoItem: Codable {
    let kind, etag: String?
    let id: YouTubeVideoItemID?
}

// MARK: - YouTubeItemID
struct YouTubeVideoItemID: Codable {
    let kind, channelID, videoID: String?

    enum CodingKeys: String, CodingKey {
        case kind
        case channelID = "channelId"
        case videoID = "videoId"
    }
}

// MARK: - PageInfo
struct PageInfo: Codable {
    let totalResults, resultsPerPage: Int?
}
